package com.chandra.bus.service.tripschedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.payload.request.TripScheduleRequest;
import com.chandra.bus.repository.TripRepository;
import com.chandra.bus.repository.TripScheduleRepository;

@Component
public class TripScheduleServiceImpl implements TripScheduleService {

	@Autowired
	TripScheduleRepository tripScheduleRepository;

	@Autowired
	TripRepository tripRepository;
	
	public String checkIfDateIsGreaterThanToday(String requestedDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date myDate = sdf.parse(requestedDate);
		Date today = Calendar.getInstance().getTime();

		// check if requested date is less than today
		if (myDate.before(today)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Tanggal input harus lebih besar dari " + today.toString());
		} else {
			return myDate.toString();
		}
	}

	@Override
	public TripSchedule addNewTrip(TripScheduleRequest tripScheduleRequest) throws ParseException {
		
		Optional<Trip> trip = Optional.of(tripRepository.findById(tripScheduleRequest.getTripDetail()).get());

		if (!trip.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip with Id " + trip.get().getId() + "not found");
		}

		String requestDate = tripScheduleRequest.getTripDate();
		String checkedDate = checkIfDateIsGreaterThanToday(requestDate);
		
		try {
			TripSchedule tripSchedule = new TripSchedule(
					checkedDate,
					tripScheduleRequest.getAvailableSeats(),
					trip.get());

			TripSchedule newTrip = tripScheduleRepository.save(tripSchedule);

			return newTrip;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

}
