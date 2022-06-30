package com.chandra.bus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.payload.request.StopRequest;
import com.chandra.bus.repository.StopRepository;

import io.swagger.annotations.Authorization;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/stop")
public class StopController {

	@Autowired
	StopRepository stopRepository;

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "get all stops", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getAllStops() {
		return ResponseEntity.ok(stopRepository.findAll());
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "add stop", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> addStop(@Valid @RequestBody StopRequest stopReq) {
		Stop stop = new Stop(
				stopReq.getCode(),
				stopReq.getName(),
				stopReq.getDetail()
				);
		return ResponseEntity.ok(stopRepository.save(stop));
	}
}