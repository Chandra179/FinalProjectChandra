package com.chandra.bus;

import com.chandra.bus.model.user.Role;
import com.chandra.bus.model.user.User;
import com.chandra.bus.model.user.UserRoles;
import com.chandra.bus.payload.request.SignupRequest;
import com.chandra.bus.repository.RoleRepository;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.service.user.UserService;
import com.chandra.bus.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	UserRepository userRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	RoleRepository roleRepository;

	@Test
	public void getAllUser() {

		final List<User> userList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			userList.add(TestObjectFactory.createUser());
		}

		final List<User> datas = userList;
		when(userRepository.findAll()).thenReturn(datas);

		final List<User> actual = userRepository.findAll();
		assertThat(actual.size()).isEqualTo(datas.size());
	}

	@Test
	public void getUserById() {

		User user = new User(
				"chandraaa",
				"chan@gmail.com",
				passwordEncoder.encode("chan12345"),
				"chandra",
				"aja",
				"25254324");

		final User datas = user;
		Long userId = user.getId();
		when(userRepository.findById(userId)).thenReturn(Optional.of(datas));

		final User actual = userRepository.findById(userId).get();
		assertThat(actual.getUsername()).isEqualTo(datas.getUsername());
	}

	@Test
	public void registerUserRoleAdmin() {

		User regisUser = new User(
				"chandraaa",
				"chan@gmail.com",
				passwordEncoder.encode("chan12345"),
				"chandra",
				"aja",
				"25254324");

		final SignupRequest signupRequest = new SignupRequest(
				"chandraaa",
				"chan@gmail.com",
				"chandra",
				"aja",
				"25254324",
				Collections.singleton("ROLE_ADMIN"),
				passwordEncoder.encode("chan12345"));


		Role adminRole = new Role(UserRoles.ROLE_ADMIN);
		doReturn(Optional.of(adminRole)).when(roleRepository).findByName(UserRoles.ROLE_ADMIN);
		doReturn(regisUser).when(userRepository).save(any());

		User savedUser = userService.registerNewUser(signupRequest);
		assertNotNull(savedUser);
		assertThat(signupRequest.getUsername()).isEqualTo(savedUser.getUsername());
	}

	@Test
	public void registerserRoleUser() {

		User regisUser = new User(
				"chandraaa",
				"chan@gmail.com",
				passwordEncoder.encode("chan12345"),
				"chandra",
				"aja",
				"25254324");

		final SignupRequest signupRequest = new SignupRequest(
				"chandraaa",
				"chan@gmail.com",
				"chandra",
				"aja",
				"25254324",
				Collections.singleton("ROLE_USER"),
				passwordEncoder.encode("chan12345"));

		Role userRole = new Role(UserRoles.ROLE_USER);
		doReturn(Optional.of(userRole)).when(roleRepository).findByName(UserRoles.ROLE_USER);
		doReturn(regisUser).when(userRepository).save(any());

		User savedUser = userService.registerNewUser(signupRequest);
		assertNotNull(savedUser);
		assertThat(signupRequest.getUsername()).isEqualTo(savedUser.getUsername());
	}

	@Test
	public void registerUserRoleEmpty() {

		User regisUser = new User(
				"chandraaa",
				"chan@gmail.com",
				passwordEncoder.encode("chan12345"),
				"chandra",
				"aja",
				"25254324");

		final SignupRequest signupRequest = new SignupRequest(
				"chandraaa",
				"chan@gmail.com",
				"chandra",
				"aja",
				"25254324",
				Collections.emptySet(),
				passwordEncoder.encode("chan12345"));

		Role userRole = new Role(UserRoles.ROLE_USER);
		doReturn(Optional.of(userRole)).when(roleRepository).findByName(UserRoles.ROLE_USER);
		doReturn(regisUser).when(userRepository).save(any());

		User savedUser = userService.registerNewUser(signupRequest);
		assertNotNull(savedUser);
		assertThat(signupRequest.getUsername()).isEqualTo(savedUser.getUsername());
	}

	@Test
	public void registerUsernameIsAlreadyTaken() {

	}

	@Test
	public void registerEmailIsAlreadyTaken() {

	}

	@Test
	public void updatingUser() {

	}
}