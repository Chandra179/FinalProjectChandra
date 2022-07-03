package com.chandra.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.model.bus.Trip;

@SpringBootTest
public class TestObjectFactory {

	public static Trip createStop() {
		final Stop stop = new Stop();
		stop.setCode(null);
		stop.setName(null);
		stop.setDetail(null);
		return stop;
	}

	public static Trip createBus() {
		final Trip trip = new Trip();
		trip.setId(new Random().nextLong());
		trip.setAgency(null);
		trip.setBus(null);
		trip.setFare(0);
		trip.setDestStop(null);
		trip.setJourneyTime(0);
		trip.setSourceStop(null);
		return trip;
	}

	public static Trip createTrip() {
		final Trip trip = new Trip();
		trip.setId(new Random().nextLong());
		trip.setAgency(null);
		trip.setBus(null);
		trip.setFare(0);
		trip.setDestStop(null);
		trip.setJourneyTime(0);
		trip.setSourceStop(null);
		return trip;
	}

	public static List<Trip> createTripList(final int size) {
		final List<Trip> result = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			result.add(createTrip());
		}
		return result;
	}

	@Test
	public void testCreateProduct() {
		createTripList(5);
	}
}
