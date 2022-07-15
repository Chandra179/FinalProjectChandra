package com.chandra.bus;

import com.chandra.bus.model.user.Role;
import com.chandra.bus.model.user.User;
import com.chandra.bus.model.user.UserRoles;
import com.chandra.bus.payload.request.SignupRequest;
import com.chandra.bus.repository.RoleRepository;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.service.user.UserService;
import com.chandra.bus.service.user.UserServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

		final List<User> datas = TestObjectFactory.createUserList(10);
		Mockito.when(userRepository.findAll()).thenReturn(datas);

		final List<User> actual = userRepository.findAll();
		MatcherAssert.assertThat(actual.size(), Matchers.equalTo(datas.size()));
	}

	@Test
	public void getUserById() {

		final Optional<User> datas = Optional.ofNullable(TestObjectFactory.createUser());
		Long userId = TestObjectFactory.createUser().getId();
		Mockito.when(userRepository.findById(userId)).thenReturn(datas);

		final Optional<User> actual = userRepository.findById(userId);
		MatcherAssert.assertThat(actual.get().getUsername(), Matchers.equalTo(datas.get().getUsername()));
	}


	@Test
	public void registerNewUser() {

		// Role userRole = new Role(UserRoles.ROLE_USER);
		Role adminRole = new Role(UserRoles.ROLE_ADMIN);

		// List<Role> userRoles = Arrays.asList(userRole, adminRole);
		Mockito.when(roleRepository.findByName(UserRoles.ROLE_ADMIN)).thenReturn(Optional.of(adminRole));

		User regisUser = new User(
				"chandra",
				"chan@gmail.com",
				"chan12345",
				"chandra",
				"aja",
				"25254324");

		final SignupRequest signupRequest = new SignupRequest(
				"chandra",
				"chan@gmail.com",
				"chandra",
				"aja",
				"25254324",
				Collections.singleton("ROLE_ADMIN"),
				passwordEncoder.encode("chan12345"));

		Mockito.when(userService.registerNewUser(signupRequest)).thenAnswer(t -> t.getMock());
	}
}