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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.payload.request.TripScheduleRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.TripRepository;
import com.chandra.bus.repository.TripScheduleRepository;
import com.chandra.bus.service.tripschedule.TripScheduleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/tripschedules")
public class TripScheduleController {

	@Autowired
	TripScheduleRepository tripScheduleRepository;

	@Autowired
	TripRepository tripRepository;

	@Autowired
	TripScheduleService tripScheduleService;

	@GetMapping("")
	@ApiOperation(value = "get all trip schedule", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTripSchedule() {

		List<TripSchedule> tripSchedule = tripScheduleRepository.findAll();

		if (tripSchedule.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.NOT_FOUND, tripSchedule);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, tripSchedule);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "get trip schedule", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTrip(@PathVariable(value = "id") Long id) {

		try {
			TripSchedule tripSchedule = tripScheduleRepository.findById(id).get();
			return ResponseHandler.generateResponse("success", HttpStatus.OK, tripSchedule);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e.getCause());
		}
	}

	@PostMapping("")
	@ApiOperation(value = "add trip schedule", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripScheduleRequest tripScheduleRequest)
			throws ParseException {

		TripSchedule tripSchedule = tripScheduleService.addNewTrip(tripScheduleRequest);
		return ResponseHandler.generateResponse("success", HttpStatus.OK, tripSchedule);
	}
}
