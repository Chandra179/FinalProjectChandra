package com.chandra.bus.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.model.user.User;
import com.chandra.bus.payload.request.StopRequest;
import com.chandra.bus.payload.response.MessageResponse;
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
		List<Stop> stop = stopRepository.findAll();
		return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Retrieving Data", stop));
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
		Stop savedStop = stopRepository.save(stop);
		return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Adding Data", savedStop));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "update stop", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> updateStop(@PathVariable(value = "id") Long id, @Valid @RequestBody StopRequest stopReq) {

		Stop stop = stopRepository.findById(id).get();
		if (stop == null) {
			return ResponseEntity.notFound().build();
		}

		stop.setCode(stopReq.getCode());
		stop.setName(stopReq.getName());
		stop.setDetail(stopReq.getDetail());

		Stop savedStop = stopRepository.save(stop);
		return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Updating Data", savedStop));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "delete stop", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> deleteStop(@PathVariable(value = "id") Long id) {

		if (stopRepository.existsById(id)) {
			stopRepository.deleteById(id);
			return ResponseEntity.ok(new MessageResponse<Stop>(true, "Success Delete Data"));
		} else {
			return ResponseEntity.ok(new MessageResponse<Stop>(false, "ID is not found"));
		}
		
	}
}