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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.payload.request.LowerUpperValueRequest;
import com.chandra.bus.payload.request.TripRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;
import com.chandra.bus.repository.StopRepository;
import com.chandra.bus.repository.TripRepository;
import com.chandra.bus.service.trip.TripService;

import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/trip")
public class TripController {

	@Autowired
	TripRepository tripRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	StopRepository stopRepository;

	@Autowired
	TripService tripService;

	@PostMapping("")
	@ApiOperation(value = "add trip", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripRequest tripRequest) {

		Trip newTrip = tripService.addNewTrip(tripRequest);
		return ResponseEntity.ok(newTrip);
	}

	@GetMapping("")
	@ApiOperation(value = "get all trip", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTrip() {

		List<Trip> trip = tripRepository.findAll();

		if (trip.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.NOT_FOUND, trip);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, trip);
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

	@GetMapping("/bus/{id}")
	@ApiOperation(value = "get trip by bus id", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByBus(@PathVariable(value = "id") Long id) {

		try {
			Bus bus = busRepository.findById(id).get();
			List<Trip> trip = tripRepository.findByBus(bus);
			return ResponseHandler.generateResponse("success", HttpStatus.OK, trip);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination stop not found");
		}
	}

	@PostMapping("/deststop")
	@ApiOperation(value = "get trip by destination stop", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByDestStop(@RequestParam(value = "name") String name) {

		List<Trip> trip = tripRepository.findByDestStop(name);

		if (trip.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.NOT_FOUND, trip);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, trip);
	}

	@PostMapping("/sourcestop")
	@ApiOperation(value = "get trip by source stop", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripBySourceStop(@RequestParam(value = "name") String name) {

		List<Trip> trip = tripRepository.findBySourceStop(name);

		if (trip.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.NOT_FOUND, trip);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, trip);
	}

	@PostMapping("/agency")
	@ApiOperation(value = "get trip by agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByAgency(@RequestParam(value = "name") String name) {

		List<Trip> trip = tripRepository.findByAgency(name);

		if (trip.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.NOT_FOUND, trip);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, trip);
	}

}
