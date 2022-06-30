package com.chandra.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.repository.TripScheduleRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/tripschedules")
public class TripScheduleController {

	@Autowired
	TripScheduleRepository tripScheduleRepository;

	@GetMapping("")
	@ApiOperation(value = "get all user trip schedule", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTripSchedule(@PathVariable(value = "id") Long id) {
		List<TripSchedule> trip = tripScheduleRepository.findAll();
		return ResponseEntity.ok(trip);
	}
}
