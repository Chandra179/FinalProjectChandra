package com.chandra.bus.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandra.bus.model.bus.Ticket;
import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.model.user.User;
import com.chandra.bus.payload.request.TicketRequest;
import com.chandra.bus.repository.TicketRepository;
import com.chandra.bus.repository.TripScheduleRepository;
import com.chandra.bus.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TripScheduleRepository tripScheduleRepository;

	@GetMapping("/")
	@ApiOperation(value = "get all ticket", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTicket() {

		List<Ticket> ticket = ticketRepository.findAll();
		return ResponseEntity.ok(ticket);
	}

	@PostMapping("/")
	@ApiOperation(value = "book new ticket", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> bookTicket(@Valid @RequestBody TicketRequest ticketRequest) {

		User user = userRepository.findById(ticketRequest.getPassegerId()).get();
		Optional<TripSchedule> tripSchedule = tripScheduleRepository.findById(ticketRequest.getTripScheduleId());

		if (tripSchedule.get().getAvailableSeats() == 0) {
			return new ResponseEntity<>("Ticket sold out", HttpStatus.NOT_FOUND);
		}

		if (!tripSchedule.isPresent()) {
			return new ResponseEntity<>("No trip shcedule found", HttpStatus.NOT_FOUND);
		}

		Ticket ticket = new Ticket()
				.setSeatNumber(tripSchedule.get().getTripDetail().getBus().getCapacity()
						- tripSchedule.get().getAvailableSeats())
				.setCancellable(false).setJourneyDate(ticketRequest.getJourneyDate()).setPassenger(user)
				.setTripSchedule(tripSchedule.get());

		ticketRepository.save(ticket);

		tripSchedule.get().setAvailableSeats(tripSchedule.get().getAvailableSeats() - 1); // seat - 1
		tripScheduleRepository.save(tripSchedule.get());// update schedule

		return ResponseEntity.ok("");
	}
}
