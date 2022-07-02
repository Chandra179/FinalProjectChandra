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
import com.chandra.bus.payload.request.AgencyRequest;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.service.BusReservationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/agency")
public class AgencyController {

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	BusReservationService busReservationService;

	@GetMapping("/")
	@ApiOperation(value = "get all agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllAgency() {

		List<Agency> agency = agencyRepository.findAll();

		if (agency == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(agency, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "get agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAgency(@PathVariable(value = "id") Long id) {

		Agency agency = agencyRepository.findById(id).get();

		if (agency == null) {
			return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(agency, HttpStatus.OK);
	}

	@PostMapping("/")
	@ApiOperation(value = "add new agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addAgency(@Valid @RequestBody AgencyRequest agencyRequest) {

		Agency agency = busReservationService.addNewAgency(agencyRequest);
		return new ResponseEntity<>(agency, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateAgency(@PathVariable(value = "id") Long id,
			@Valid @RequestBody AgencyRequest agencyRequest) {

		Agency agency = busReservationService.updatingAgency(id, agencyRequest);
		return new ResponseEntity<>(agency, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteAgency(@PathVariable(value = "id") Long id) {

		agencyRepository.deleteById(id);
		String result = "Success Deleting Data with Id: " + id;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
