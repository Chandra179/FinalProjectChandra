package com.chandra.bus;

import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chandra.bus.model.user.User;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.service.user.UserService;
import com.chandra.bus.service.user.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	UserRepository userRepository;

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
	@Disabled
	public void registerNewUser() {

		final User datas = TestObjectFactory.createUser();
		Mockito.when(userRepository.save(datas)).thenReturn(datas);

		final Optional<User> actual = userRepository.findByUsername(datas.getUsername());
		MatcherAssert.assertThat(actual.get().getUsername(), Matchers.equalTo(datas.getUsername()));
		MatcherAssert.assertThat(actual.get().getEmail(), Matchers.equalTo(datas.getEmail()));
	}
}