package com.chandra.bus.controller;

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

import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.payload.request.TripScheduleRequest;
import com.chandra.bus.repository.TripRepository;
import com.chandra.bus.repository.TripScheduleRepository;

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

	@GetMapping("/")
	@ApiOperation(value = "get all trip schedule", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTripSchedule() {
		List<TripSchedule> trip = tripScheduleRepository.findAll();
		return ResponseEntity.ok(trip);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "get trip schedule", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTrip(@PathVariable(value = "id") Long id) {
		TripSchedule tripSchedule = tripScheduleRepository.findById(id).get();
		if (tripSchedule == null) {
			return new ResponseEntity<>("No trip schedule found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(tripSchedule);
	}

	@PostMapping("/")
	@ApiOperation(value = "add trip schedule", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripScheduleRequest tripScheduleRequest) {

		Trip trip = tripRepository.findById(tripScheduleRequest.getTripDetail()).get();

		TripSchedule tripSchedule = new TripSchedule(tripScheduleRequest.getTripDate(),
				tripScheduleRequest.getAvailableSeats(), trip);

		return ResponseEntity.ok(tripSchedule);
	}
}
