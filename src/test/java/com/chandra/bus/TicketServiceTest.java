package com.chandra.bus;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.chandra.bus.repository.TicketRepository;
import com.chandra.bus.repository.TripScheduleRepository;
import com.chandra.bus.repository.UserRepository;
import com.chandra.bus.service.ticket.TicketService;
import com.chandra.bus.service.ticket.TicketServiceImpl;

@SpringBootTest
class TicketServiceTest {

	@InjectMocks
	private TicketService ticketService = new TicketServiceImpl();

	@Mock
	TicketRepository ticketRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	TripScheduleRepository tripScheduleRepository;

	@SuppressWarnings("deprecation")
	@BeforeAll
	public void setup() {
		MockitoAnnotations.initMocks(ticketRepository);
		MockitoAnnotations.initMocks(userRepository);
		MockitoAnnotations.initMocks(tripScheduleRepository);

		ReflectionTestUtils.setField(ticketService, "ticketRepository", ticketRepository);
		ReflectionTestUtils.setField(ticketService, "userRepository", userRepository);
		ReflectionTestUtils.setField(ticketService, "tripScheduleRepository", tripScheduleRepository);
	}

	@Test
	public void testFindAll() {

		final List<Product> datas = TestObjectFactory.createProductList(10);
		Mockito.when(productRepository.findAll()).thenReturn(datas);

		final List<Product> actual = productService.findAllProduct();
		MatcherAssert.assertThat(actual.size(), Matchers.equalTo(datas.size()));
	}

}
