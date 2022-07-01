package com.chandra.bus.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.chandra.bus.model.user.Role;
import com.chandra.bus.model.user.User;
import com.chandra.bus.model.user.UserRoles;
import com.chandra.bus.payload.request.SignupRequest;
import com.chandra.bus.payload.request.UserCustomRequest;
import com.chandra.bus.payload.request.UserPasswordRequest;
import com.chandra.bus.payload.response.MessageResponse;

import com.chandra.bus.repository.RoleRepository;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.security.jwt.JwtUtils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET })
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("/")
	@ApiOperation(value = "get all user", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllUser() {
		List<User> user = userRepository.findAll();
		if (user.isEmpty()) {
			return ResponseEntity.badRequest().body(new MessageResponse<String>("No users found"));
		}
		return ResponseEntity.ok(user);
	}

	
	
	@GetMapping("/{id}")
	@ApiOperation(value = "get user by id", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {
		User user = userRepository.findById(id).get();
		if (user == null) {
			return ResponseEntity.badRequest().body(new MessageResponse<String>("User with ID " + id + " not found"));
		}
		return ResponseEntity.ok(user);
	}

	
	
	@PostMapping("/signup")
	@ApiOperation(value = "register new user")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest SignupRequest) {
		if (userRepository.existsByUsername(SignupRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse<String>("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(SignupRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse<String>("Error: Email is already in use!"));
		}

		// create new user account
		User user = new User(
				SignupRequest.getUsername(),
				SignupRequest.getEmail(),
				encoder.encode(SignupRequest.getPassword()),
				SignupRequest.getFirstName(),
				SignupRequest.getLastName(),
				SignupRequest.getMobileNumber()
				);

		Set<String> strRoles = SignupRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(UserRoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByName(UserRoles.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(UserRoles.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse<String>("User registered successfully!"));
	}
	
	

	@PutMapping("/{id}")
	@ApiOperation(value = "udpate user", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserCustomRequest userCustomRequest) {
		User user = userRepository.findById(id).get();
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setFirstName(userCustomRequest.getFirstName());
		user.setLastName(userCustomRequest.getLastName());
		user.setMobileNumber(userCustomRequest.getMobileNumber());

		User updatedUser = userRepository.save(user);

		return ResponseEntity.ok(updatedUser);
	}

	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "delete user", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
		User user = userRepository.findById(id).get();
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		userRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse<String>("Success delete user!"));
	}
}
