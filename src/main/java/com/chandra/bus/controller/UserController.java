package com.chandra.bus.controller;

import com.chandra.bus.model.user.User;
import com.chandra.bus.payload.request.SignupRequest;
import com.chandra.bus.payload.request.UserRequest;
import com.chandra.bus.payload.response.ResponseHandler;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.service.user.UserService;
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

@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET })
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@GetMapping("")
	@ApiOperation(value = "get all user", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllUser() {

		List<User> user = userRepository.findAll();
		if (user.isEmpty()) {
			return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(user);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "get user by id", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {

		try {
			User user = userRepository.findById(id).get();
			return ResponseHandler.generateResponse("success", HttpStatus.OK, user);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e.getCause());
		}
	}

	@PostMapping("/signup")
	@ApiOperation(value = "register new user")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest SignupRequest) {

		User newUser = userService.registerNewUser(SignupRequest);
		return ResponseEntity.ok(newUser);
	}

	@PutMapping("")
	@ApiOperation(value = "udpate user", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequest userRequest) {

		User udpatedUser = userService.updatingUser(userRequest);
		return ResponseEntity.ok(udpatedUser);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete user", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {

		try {
			userRepository.deleteById(id);
			String result = "Success Delete User with Id: " + id;
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}
}
