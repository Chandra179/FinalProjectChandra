package com.chandra.bus;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.model.user.User;
import com.chandra.bus.payload.request.TripRequest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class TestObjectFactory {

	public static User createUser() {
		User user = new User(
				"chandraaa",
				"chan@gmail.com",
				"chan12345",
				"chandra",
				"aja",
				"25254324");
		return user;
	}
	public static Stop createSourceStop() {
		final Stop stop = new Stop();
		stop.setCode("1-7");
		stop.setName("Kemahi");
		stop.setDetail("kemahi 1-9");
		return stop;
	}

	public static Stop createDestStop() {
		final Stop stop = new Stop();
		stop.setCode("1-8");
		stop.setName("sukarang");
		stop.setDetail("Cemahi 1-8");
		return stop;
	}

	public static Agency createAgency() {
		final Agency agency = new Agency();
		agency.setCode("JML");
		agency.setName("Bis tua");
		agency.setDetails("PT.Suka Maju");
		return agency;
	}

	public static Bus createBus() {
		final Bus bus = new Bus();
		bus.setCode("BLK22");
		bus.setAgency(createAgency());
		bus.setCapacity(40);
		bus.setMake("20");
		return bus;
	}

	public static TripRequest createTripRequest() {
		final TripRequest trip = new TripRequest(1L, 1, 1, 1L, 1L, 1L, 1L);
		return trip;
	}

	public static Trip createTrip() {
		final Trip trip = new Trip();
		trip.setId(new Random().nextLong());
		trip.setAgency(createAgency());
		trip.setBus(createBus());
		trip.setFare(20000);
		trip.setDestStop(createDestStop());
		trip.setJourneyTime(200);
		trip.setSourceStop(createSourceStop());
		return trip;
	}
}
