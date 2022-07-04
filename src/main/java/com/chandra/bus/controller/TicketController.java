package com.chandra.bus.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Ticket;
import com.chandra.bus.payload.request.TicketRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.TicketRepository;
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

	@GetMapping("")
	@ApiOperation(value = "get current user ticket", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getCurrentUserTicket() {

		// get logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = auth.getName();

		// get ticket from current user
		List<Ticket> userTicket = ticketRepository.findByPassenger(currentUser);

		// if user doesn't have a ticket
		if (userTicket.isEmpty()) {
			return ResponseHandler.generateResponse("No ticket found", HttpStatus.NOT_FOUND, userTicket);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, userTicket);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update ticket", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateTicket(@PathVariable(value = "id") Long id,
			@Valid @RequestBody TicketRequest ticketReq) throws ParseException {

		Ticket updatedTicket = ticketService.updatingTicket(id, ticketReq);
		return ResponseHandler.generateResponse("success", HttpStatus.OK, updatedTicket);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete ticket", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteTicket(@PathVariable(value = "id") Long id) throws ParseException {

		try {
			ticketRepository.deleteById(id);
			String result = "Success Delete Ticket with Id: " + id;
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}
}
