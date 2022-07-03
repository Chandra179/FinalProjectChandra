package com.chandra.bus.service.user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.user.Role;
import com.chandra.bus.model.user.User;
import com.chandra.bus.model.user.UserRoles;
import com.chandra.bus.payload.request.SignupRequest;
import com.chandra.bus.payload.request.UserRequest;
import com.chandra.bus.repository.RoleRepository;
import com.chandra.bus.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	public void checkIfUserAlreadyRegistered(SignupRequest SignupRequest) {

		if (userRepository.existsByUsername(SignupRequest.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Username is already taken!");
		}
		if (userRepository.existsByEmail(SignupRequest.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Email is already in use!");
		}
	}

	public Set<Role> handleUserRole(Set<String> strRoles) {

		Set<Role> roles = new HashSet<>();
		// if user not add role, then assign it to ROLE_USER
		if (strRoles.isEmpty()) {
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
		return roles;
	}

	@Override
	public User registerNewUser(SignupRequest SignupRequest) {

		checkIfUserAlreadyRegistered(SignupRequest);

		// create new user account
		User user = new User(
				SignupRequest.getUsername(),
				SignupRequest.getEmail(),
				encoder.encode(SignupRequest.getPassword()),
				SignupRequest.getFirstName(),
				SignupRequest.getLastName(),
				SignupRequest.getMobileNumber());

		Set<String> strRoles = SignupRequest.getRole();
		Set<Role> roles = handleUserRole(strRoles);

		user.setRoles(roles);
		User savedUser = userRepository.save(user);

		return savedUser;
	}

	@Override
	public User updatingUser(Long id, UserRequest userRequest) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}

		user.get().setFirstName(userRequest.getFirstName());
		user.get().setLastName(userRequest.getLastName());
		user.get().setMobileNumber(userRequest.getMobileNumber());

		User updatedUser = userRepository.save(user.get());
		return updatedUser;
	}

}
