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

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.payload.request.BusCustomRequest;
import com.chandra.bus.payload.response.MessageResponse;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;

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

	@PostMapping("/")
	@ApiOperation(value = "add new bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addBusByUserId(@Valid @RequestBody BusCustomRequest busCustomRequest) {

		// check apakah agency ada
		Agency agency = agencyRepository.findById(busCustomRequest.getAgencyId()).get();
		if (agency == null) {
			return new ResponseEntity<>("No Agency found", HttpStatus.NOT_FOUND);
		}

		Bus bus = new Bus(busCustomRequest.getCode(), busCustomRequest.getCapacity(), busCustomRequest.getMake(),
				agency);
		Bus savedBus = busRepository.save(bus);
		return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Adding Data", savedBus));
	}

	@GetMapping("/")
	@ApiOperation(value = "get all bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllBus() {
		List<Bus> bus = busRepository.findAll();
		if (bus == null) {
			return new ResponseEntity<>("No Bus found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Retrieving Data", bus));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "get bus by id", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getBusById(@PathVariable(value = "id") Long id) {
		Bus bus = busRepository.findById(id).get();
		if (bus == null) {
			return new ResponseEntity<>("No Bus found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Retrieving Data", bus));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateBus(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BusCustomRequest busCustomRequest) {

		Bus bus = busRepository.findById(id).get();
		if (bus == null) {
			return new ResponseEntity<>("No Bus found", HttpStatus.NOT_FOUND);
		}

		Agency agency = agencyRepository.findById(busCustomRequest.getAgencyId()).get();

		bus.setCode(busCustomRequest.getCode());
		bus.setCapacity(busCustomRequest.getCapacity());
		bus.setMake(busCustomRequest.getMake());
		bus.setAgency(agency);

		Bus savedBus = busRepository.save(bus);
		return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Updating Data", savedBus));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete bus", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteBus(@PathVariable(value = "id") Long id) {

		Bus bus = busRepository.findById(id).get();
		if (bus == null) {
			return ResponseEntity.notFound().build();
		}
		busRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse<String>("Success Delete Bus"));
	}

}