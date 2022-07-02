package com.chandra.bus.service.trip;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.payload.request.TripRequest;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;
import com.chandra.bus.repository.StopRepository;
import com.chandra.bus.repository.TripRepository;

@Component
public class TripServiceImpl implements TripService {

	@Autowired
	TripRepository tripRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	StopRepository stopRepository;

	@Override
	public Trip addNewTrip(TripRequest tripRequest) {
		// TODO Auto-generated method stub

		Optional<Agency> agency = agencyRepository.findById(tripRequest.getAgencyId());
		if (!agency.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agency not found");
		}

		Optional<Bus> bus = busRepository.findById(tripRequest.getBusId());
		if (!bus.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found");
		}

		Optional<Stop> sourceStop = stopRepository.findById(tripRequest.getSourceStopId());
		if (!sourceStop.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Source stop not found");
		}

		Optional<Stop> destStop = stopRepository.findById(tripRequest.getDestStopId());
		if (!destStop.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination stop not found");
		}

		try {
			Trip trip = new Trip(
					tripRequest.getFare(),
					tripRequest.getJourneyTime(),
					sourceStop.get(),
					destStop.get(),
					bus.get(),
					agency.get());

			Trip savedTrip = tripRepository.save(trip);
			return savedTrip;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}
}
