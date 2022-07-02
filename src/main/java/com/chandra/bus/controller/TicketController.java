package com.chandra.bus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandra.bus.model.bus.Ticket;
import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.payload.request.TicketRequest;
import com.chandra.bus.repository.TicketRepository;
import com.chandra.bus.repository.TripScheduleRepository;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.service.ticket.TicketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketService ticketService;

	@GetMapping("/{id}")
	@ApiOperation(value = "get ticket", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTicket(@PathVariable(value = "id") Long id) {

		Ticket ticket = ticketRepository.findById(id).get();
		return ResponseEntity.ok(ticket);
	}

	@PostMapping("/bookticket")
	@ApiOperation(value = "book new ticket", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> bookTicket(@Valid @RequestBody TicketRequest ticketRequest) {

		TripSchedule tripSchedule = ticketService.bookingTicket(ticketRequest);
		return ResponseEntity.ok(tripSchedule);
	}
}
