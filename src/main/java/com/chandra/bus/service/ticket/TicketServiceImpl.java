package com.chandra.bus.service.ticket;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	@Override
	public TripSchedule bookingTicket(TicketRequest ticketRequest) {
		User user = userRepository.findById(ticketRequest.getPassegerId()).get();
		Optional<TripSchedule> tripSchedule = tripScheduleRepository.findById(ticketRequest.getTripScheduleId());

		String journeyDate = ticketRequest.getJourneyDate();

		if (!tripSchedule.isPresent()) {
			// new ResponseEntity<>("No trip shcedule found", HttpStatus.NO_CONTENT);
			throw new NoSuchElementException("Trip schedule not found");
		}

		if (tripSchedule.get().getAvailableSeats() == 0) {
			// return new ResponseEntity<>("Ticket sold out", HttpStatus.NOT_FOUND);
			throw new NoSuchElementException("Ticked sold out");
		}

		if (!tripSchedule.get().getTripDate().equals(journeyDate)) {
			// return new ResponseEntity<>("No trip found at date " + journeyDate,
			// HttpStatus.NOT_FOUND);
			throw new NoSuchElementException("No trip found at given date");
		}

		Ticket ticket = new Ticket()
				.setSeatNumber(tripSchedule.get().getTripDetail().getBus().getCapacity()
						- tripSchedule.get().getAvailableSeats())
				.setCancellable(false).setJourneyDate(ticketRequest.getJourneyDate()).setPassenger(user)
				.setTripSchedule(tripSchedule.get());

		ticketRepository.save(ticket);

		tripSchedule.get().setAvailableSeats(tripSchedule.get().getAvailableSeats() - 1); // seat - 1
		TripSchedule savedTrip = tripScheduleRepository.save(tripSchedule.get());// update schedule

		return savedTrip;
	}

}
