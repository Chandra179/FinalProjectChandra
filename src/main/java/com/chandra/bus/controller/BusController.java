package com.chandra.bus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.payload.request.BusRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;
import com.chandra.bus.service.bus.BusService;

import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

	@Autowired
	BusRepository busRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	BusService busService;

	@PostMapping("")
	@ApiOperation(value = "add new bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addBus(@Valid @RequestBody BusRequest busRequest) {

		Bus bus = busService.addNewBus(busRequest);
		return ResponseHandler.generateResponse("success", HttpStatus.OK, bus);
	}

	@GetMapping("")
	@ApiOperation(value = "get all bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllBus() {

		List<Bus> bus = busRepository.findAll();
		if (bus.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.OK, bus);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, bus);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "get bus by id", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getBus(@PathVariable(value = "id") Long id) {
		
		try {
			Bus bus = busRepository.findById(id).get();
			return ResponseHandler.generateResponse("success", HttpStatus.OK, bus);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e.getCause());
		}
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateBus(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BusRequest busRequest) {

		Bus bus = busService.updatingBus(id, busRequest);
		return ResponseEntity.ok(bus);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteBus(@PathVariable(value = "id") Long id) {

		try {
			busRepository.deleteById(id);
			String result = "Success Delete Bus with Id: " + id;
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}
}