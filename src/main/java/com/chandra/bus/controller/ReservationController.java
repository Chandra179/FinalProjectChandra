package com.chandra.bus.controller;

import java.text.ParseException;
import java.util.List;

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
import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.payload.request.LowerUpperValueRequest;
import com.chandra.bus.payload.request.TicketRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.StopRepository;
import com.chandra.bus.repository.TripRepository;
import com.chandra.bus.repository.TripScheduleRepository;
import com.chandra.bus.service.ticket.TicketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

	@Autowired
	StopRepository stopRepository;

	@Autowired
	TripRepository tripRepository;

	@Autowired
	TicketService ticketService;

	@Autowired
	TripScheduleRepository tripScheduleRepository;

	@PostMapping("/bookticket")
	@ApiOperation(value = "book new ticket", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> bookTicket(@Valid @RequestBody TicketRequest ticketRequest) throws ParseException {

		Ticket ticket = ticketService.bookingTicket(ticketRequest);
		return ResponseEntity.ok(ticket);
	}

	@GetMapping("/tripschedules")
	@ApiOperation(value = "get all trip schedule", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTripSchedule() {

		List<TripSchedule> tripSchedule = tripScheduleRepository.findAll();

		if (tripSchedule.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.NOT_FOUND, tripSchedule);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, tripSchedule);
	}

	@PostMapping("/fare")
	@ApiOperation(value = "get trip by fare", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByFare(@Valid @RequestBody LowerUpperValueRequest lowerUpperValueRequest) {

		Integer minFare = lowerUpperValueRequest.getLowerValue();
		Integer maxFare = lowerUpperValueRequest.getUpperValue();

		List<Trip> trip = tripRepository.findByFareBetween(minFare, maxFare);

		if (trip.isEmpty()) {
			String respFormat = String.format("Trip with fare %d - %d not found", minFare, maxFare);
			return ResponseHandler.generateResponse(respFormat, HttpStatus.NOT_FOUND, trip);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, trip);
	}

	@PostMapping("/journeytime")
	@ApiOperation(value = "get trip by journey time", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getJourneyTimeBetween(@Valid @RequestBody LowerUpperValueRequest lowerUpperValueRequest) {

		Integer minJourneyTime = lowerUpperValueRequest.getLowerValue();
		Integer maxJourneyTime = lowerUpperValueRequest.getUpperValue();

		List<Trip> trip = tripRepository.findByJourneyTimeBetween(minJourneyTime, maxJourneyTime);

		if (trip.isEmpty()) {
			String respFormat = String.format("Trip with journey time %d - %d not found", minJourneyTime,
					maxJourneyTime);
			return ResponseHandler.generateResponse(respFormat, HttpStatus.NOT_FOUND, trip);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, trip);
	}

	@PostMapping("/stop")
	@ApiOperation(value = "get trip by source - destination stop", authorizations = {
			@Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByStop(@Valid @RequestBody LowerUpperValueRequest lowerUpperValueRequest) {

		Integer sourceStop = lowerUpperValueRequest.getLowerValue();
		Integer destStop = lowerUpperValueRequest.getUpperValue();

		List<Trip> trip = tripRepository.findTripsByStops(sourceStop, destStop);

		if (trip.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.NOT_FOUND, trip);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, trip);
	}
}
