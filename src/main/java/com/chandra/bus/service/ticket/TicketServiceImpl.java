package com.chandra.bus.service.ticket;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Ticket;
import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.model.user.User;
import com.chandra.bus.payload.request.TicketRequest;
import com.chandra.bus.repository.TicketRepository;
import com.chandra.bus.repository.TripScheduleRepository;
import com.chandra.bus.repository.UserRepository;

@Component
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TripScheduleRepository tripScheduleRepository;

	public Optional<User> checkIfUserPresent() {

		// get logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = auth.getName();

		// get user from database
		Optional<User> user = userRepository.findByUsername(currentUser);

		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
		}
		return user;
	}

	public Optional<TripSchedule> checkIfTripScheduleAvailable(TicketRequest ticketRequest) {

		// find trip schedule by id
		Optional<TripSchedule> tripSchedule = tripScheduleRepository.findById(ticketRequest.getTripScheduleId());

		String journeyDate = ticketRequest.getJourneyDate();

		if (!tripSchedule.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip schedule not found");

		} else if (tripSchedule.get().getAvailableSeats() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticked sold out");

		} else if (!tripSchedule.get().getTripDate().equals(journeyDate)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trip found at date " + journeyDate);
		}
		return tripSchedule;
	}

	@Override
	public TripSchedule bookingTicket(TicketRequest ticketRequest) {

		Optional<User> user = checkIfUserPresent();
		Optional<TripSchedule> tripSchedule = checkIfTripScheduleAvailable(ticketRequest);
		
		try {
			Ticket ticket = new Ticket()
					// kursi passenger dimulai dari descending 30, 29, 28, .... 1
					.setSeatNumber(tripSchedule.get().getTripDetail().getBus().getCapacity() - tripSchedule.get().getAvailableSeats())
					.setCancellable(false)
					.setJourneyDate(ticketRequest.getJourneyDate())
					.setPassenger(user.get())
					.setTripSchedule(tripSchedule.get());

			ticketRepository.save(ticket);

			// setiap (1) tiket yang dibeli akan mengurangi kursi sebanyak (1)
			tripSchedule.get().setAvailableSeats(tripSchedule.get().getAvailableSeats() - 1);

			// update trip schedule
			TripSchedule savedTrip = tripScheduleRepository.save(tripSchedule.get());

			return savedTrip;
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

}
