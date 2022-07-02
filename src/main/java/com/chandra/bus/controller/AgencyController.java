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

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.payload.request.AgencyRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.service.agency.AgencyService;

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
	AgencyService agencyService;

	@GetMapping("")
	@ApiOperation(value = "get all agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllAgency() {

		List<Agency> agency = agencyRepository.findAll();
		if (agency.isEmpty()) {
			return ResponseHandler.generateResponse("No data found", HttpStatus.NOT_FOUND, agency);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, agency);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "get agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAgency(@PathVariable(value = "id") Long id) {

		try {
			Agency agency = agencyRepository.findById(id).get();
			return ResponseHandler.generateResponse("success", HttpStatus.OK, agency);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e.getCause());
		}
	}

	@PostMapping("")
	@ApiOperation(value = "add new agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addAgency(@Valid @RequestBody AgencyRequest agencyRequest) {

		Agency agency = agencyService.addNewAgency(agencyRequest);
		return ResponseHandler.generateResponse("success", HttpStatus.OK, agency);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateAgency(@PathVariable(value = "id") Long id,
			@Valid @RequestBody AgencyRequest agencyRequest) {

		Agency agency = agencyService.updatingAgency(id, agencyRequest);
		return new ResponseEntity<>(agency, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete agency", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteAgency(@PathVariable(value = "id") Long id) {

		try {
			agencyRepository.deleteById(id);
			String result = "Success Delete Agency with Id: " + id;
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}
}
