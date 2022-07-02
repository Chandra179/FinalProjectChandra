package com.chandra.bus.controller;

import java.util.List;
import java.util.Optional;

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

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.payload.request.LowerUpperValueRequest;
import com.chandra.bus.payload.request.TripRequest;
import com.chandra.bus.payload.response.MessageResponse;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;
import com.chandra.bus.repository.StopRepository;
import com.chandra.bus.repository.TripRepository;

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

	@PostMapping("/")
	@ApiOperation(value = "add trip", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripRequest tripRequest) {

		Agency agency = agencyRepository.findById(tripRequest.getAgencyId()).get();
		Bus bus = busRepository.findById(tripRequest.getBusId()).get();
		Stop sourceStop = stopRepository.findById(tripRequest.getSourceStopId()).get();
		Stop destStop = stopRepository.findById(tripRequest.getDestStopId()).get();

		Trip trip = new Trip(tripRequest.getFare(), tripRequest.getJourneyTime(), sourceStop, destStop, bus, agency);
		Trip savedTrip = tripRepository.save(trip);
		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Adding Data", savedTrip));
	}

	@GetMapping("/")
	@ApiOperation(value = "get all trip", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTrip() {

		List<Trip> trip = tripRepository.findAll();

		if (trip.isEmpty()) {
			return new ResponseEntity<>("No trip found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Retrieving Data", trip));
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
			return new ResponseEntity<>(respFormat, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Retrieving Data", trip));
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
			return new ResponseEntity<>(respFormat, HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Retrieving Data", trip));
	}

	@PostMapping("/stop")
	@ApiOperation(value = "get trip by source - dest stop", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByStop(@Valid @RequestBody LowerUpperValueRequest lowerUpperValueRequest) {

		Integer sourceStop = lowerUpperValueRequest.getLowerValue();
		Integer destStop = lowerUpperValueRequest.getUpperValue();

		List<Trip> trip = tripRepository.findTripsByStops(sourceStop, destStop);

		if (trip.isEmpty()) {
			return new ResponseEntity<>(new MessageResponse<Trip>(false, "No trip found"), HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Retrieving Data", trip));
	}

	@GetMapping("/bus/{id}")
	@ApiOperation(value = "get trip by bus id", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByBus(@PathVariable(value = "id") Long id) {

		Optional<Bus> bus = busRepository.findById(id);

		if (!bus.isPresent()) {
			return new ResponseEntity<>(new MessageResponse<Bus>(false, "No trip found"), HttpStatus.NOT_FOUND);
		}

		List<Trip> trip = tripRepository.findByBus(bus);
		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Retrieving Data", trip));
	}

	@PostMapping("/deststop")
	@ApiOperation(value = "get trip by destination stop", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByDestStop(@RequestParam(value = "name") String name) {

		List<Trip> trip = tripRepository.findByDestStop(name);

		if (trip.isEmpty()) {
			return new ResponseEntity<>(new MessageResponse<Trip>(false, "No Stop found"), HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Retrieving Data", trip));
	}

	@PostMapping("/sourcestop")
	@ApiOperation(value = "get trip by source stop", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripBySourceStop(@RequestParam(value = "name") String name) {

		List<Trip> trip = tripRepository.findBySourceStop(name);

		if (trip.isEmpty()) {
			return new ResponseEntity<>(new MessageResponse<Trip>(false, "No Stop found"), HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Retrieving Data", trip));
	}

	@PostMapping("/agency")
	@ApiOperation(value = "get trip by agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getTripByAgency(@RequestParam(value = "name") String name) {

		List<Trip> trip = tripRepository.findByAgency(name);

		if (trip.isEmpty()) {
			return new ResponseEntity<>(new MessageResponse<Trip>(false, "No Agency found"), HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new MessageResponse<Trip>(true, "Success Retrieving Data", trip));
	}

}
