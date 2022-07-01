package com.chandra.bus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/{id}")
	@ApiOperation(value = "get bus agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getBusByAgencyId(@PathVariable(value = "id") Long id) {
		List<Bus> bus = busRepository.findByAgencyId(id);
		return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Retrieving Data", bus));
	}

	@PostMapping("/{id}")
	@ApiOperation(value = "add new bus by owner id", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addBusByUserId(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BusCustomRequest busCustomRequest) {
		Agency agency = agencyRepository.findByOwnerUser(id);
		Bus bus = new Bus(
				busCustomRequest.getCode(),
				busCustomRequest.getCapacity(),
				busCustomRequest.getMake(),
				agency
		);
		Bus savedBus = busRepository.save(bus);
		return ResponseEntity.ok(new MessageResponse<Bus>(true, "Success Adding Data", savedBus));
	}
}