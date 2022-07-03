package com.chandra.bus;


import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.payload.request.TripRequest;
import com.chandra.bus.repository.TripRepository;
import com.chandra.bus.service.trip.TripService;
import com.chandra.bus.service.trip.TripServiceImpl;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

	@InjectMocks
	private TripService tripService = new TripServiceImpl();

	@Mock
	TripRepository tripRepository;

//	@Test
//	public void addTrip() {
//
//		TripRequest datas = TestObjectFactory.createTripRequest();
//		Mockito.when(tripService.addNewTrip(datas)).then(true);
//		Trip newTrip = tripService.addNewTrip(datas);
//
//		final List<Trip> datas = TestObjectFactory.createTripList(10);
//		Mockito.when(tripRepository.findAll()).thenReturn(datas);
//
//		final List<Trip> actual = tripRepository.findAll();
//		MatcherAssert.assertThat(actual.size(), Matchers.equalTo(datas.size()));
//	}

	@Test
	public void getAllTrip() {

		final List<Trip> datas = TestObjectFactory.createTripList(10);
		Mockito.when(tripRepository.findAll()).thenReturn(datas);

		final List<Trip> actual = tripRepository.findAll();
		MatcherAssert.assertThat(actual.size(), Matchers.equalTo(datas.size()));
	}
}