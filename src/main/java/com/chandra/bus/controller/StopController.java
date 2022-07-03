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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.payload.request.StopRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.StopRepository;
import com.chandra.bus.service.stop.StopService;

import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/stop")
public class StopController {

	@Autowired
	StopRepository stopRepository;

	@Autowired
	StopService stopService;

	@GetMapping("")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "get all stop", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getAllStops() {

		List<Stop> stop = stopRepository.findAll();
		if (stop.isEmpty()) {
			return ResponseHandler.generateResponse("success", HttpStatus.NOT_FOUND, stop);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, stop);
	}

	@GetMapping("/{name}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "get stop by name", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getStopByName(@RequestParam(value = "name") String name) {

		List<Stop> stop = stopRepository.findByName(name);
		if (stop.isEmpty()) {
			return ResponseHandler.generateResponse("success", HttpStatus.NOT_FOUND, stop);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, stop);
	}

	@GetMapping("/{code}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "get stop by code", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getStopByCode(@RequestParam(value = "code") String code) {

		List<Stop> stop = stopRepository.findByCode(code);
		if (stop.isEmpty()) {
			return ResponseHandler.generateResponse("Not found", HttpStatus.NOT_FOUND, stop);
		}
		return ResponseHandler.generateResponse("success", HttpStatus.OK, stop);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "add stop", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> addStop(@Valid @RequestBody StopRequest stopReq) {

		Stop stop = stopService.addNewStop(stopReq);
		return ResponseHandler.generateResponse("success", HttpStatus.OK, stop);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "update stop", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> updateStop(@PathVariable(value = "id") Long id, @Valid @RequestBody StopRequest stopReq) {

		Stop stop = stopService.updatingStop(id, stopReq);
		return ResponseHandler.generateResponse("success", HttpStatus.OK, stop);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "delete stop", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> deleteStop(@PathVariable(value = "id") Long id) {

		try {
			stopRepository.deleteById(id);
			String result = "Success Delete Stop with Id: " + id;
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}
}