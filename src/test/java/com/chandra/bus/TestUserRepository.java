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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
		when(userRepository.findAll()).thenReturn(datas);

		final List<User> actual = userRepository.findAll();
		assertThat(actual.size()).isEqualTo(datas.size());
	}

	@Test
	public void getUserById() {

		final Optional<User> datas = Optional.ofNullable(TestObjectFactory.createUser());
		Long userId = TestObjectFactory.createUser().getId();
		when(userRepository.findById(userId)).thenReturn(datas);

		final Optional<User> actual = userRepository.findById(userId);
		assertThat(actual.get().getUsername()).isEqualTo(datas.get().getUsername());
	}

	@Test
	public void registerNewUser() {

		Role adminRole = new Role(UserRoles.ROLE_ADMIN);
		when(roleRepository.findByName(UserRoles.ROLE_ADMIN)).thenReturn(Optional.of(adminRole));

		User regisUser = new User(
				"chandraaa",
				"chan@gmail.com",
				passwordEncoder.encode("chan12345"),
				"chandra",
				"aja",
				"25254324");

		final SignupRequest signupRequest = new SignupRequest(
				"dsadsadasd",
				"chaasdasdasn12312@gmail.com",
				"chandra",
				"aja",
				"25254324",
				Collections.singleton("ROLE_ADMIN"),
				passwordEncoder.encode("chan12345"));

		doReturn(regisUser).when(userRepository).save(any());

		User savedUser = userService.registerNewUser(signupRequest);
		assertNotNull(savedUser);
		assertThat(regisUser.getUsername()).isEqualTo(savedUser.getUsername());
	}
}