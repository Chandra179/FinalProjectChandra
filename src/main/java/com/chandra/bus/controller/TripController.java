package com.chandra.bus.controller;

import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.payload.request.TripRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.BusRepository;
import com.chandra.bus.repository.TripRepository;
import com.chandra.bus.service.trip.TripService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/trip")
public class TripController {

	@Autowired
	TripRepository tripRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	TripService tripService;

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

	@PostMapping("")
	@ApiOperation(value = "add trip", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripRequest tripRequest) {

		Trip newTrip = tripService.addNewTrip(tripRequest);
		return ResponseHandler.generateResponse("success", HttpStatus.OK, newTrip);
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

	@PutMapping("/{id}")
	@ApiOperation(value = "update trip", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateTrip(@PathVariable(value = "id") Long id,
			@Valid @RequestBody TripRequest tripRequest) {

		Trip updatedTrip = tripService.updatingTrip(id, tripRequest);
		return ResponseHandler.generateResponse("success", HttpStatus.OK, updatedTrip);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "delete trip", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> deleteTrip(@PathVariable(value = "id") Long id) {

		try {
			tripRepository.deleteById(id);
			String result = "Success Delete Trip with Id: " + id;
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

}
