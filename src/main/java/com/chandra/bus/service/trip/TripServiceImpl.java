package com.chandra.bus.service.trip;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.payload.request.TripRequest;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;
import com.chandra.bus.repository.StopRepository;
import com.chandra.bus.repository.TripRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Class untuk handling Trip
 * 
 * @since 1.0
 */
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

	@Getter
	@Setter
	final class CollectionOfId {
		public Bus bus;
		public Stop sourceStop;
		public Stop destStop;

		public CollectionOfId(Optional<Bus> bus, Optional<Stop> sourceStop, Optional<Stop> destStop) {
			this.bus = bus.get();
			this.sourceStop = sourceStop.get();
			this.destStop = destStop.get();
		}
	}

	public CollectionOfId checkIfIdExist(Optional<Agency> agency, TripRequest tripRequest) {
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
		
		return new CollectionOfId(bus, sourceStop, destStop);
	}

	/**
	 * Method untuk membuat Trip baru
	 * 
	 * @param tripRequest payload TripRequest
	 * @return model Trip
	 */
	@Override
	public Trip addNewTrip(TripRequest tripRequest) {
		// TODO Auto-generated method stub

		Optional<Agency> agency = agencyRepository.findById(tripRequest.getAgencyId());
		CollectionOfId collectionOfId = checkIfIdExist(agency, tripRequest);

		Trip trip = new Trip(
				tripRequest.getFare(),
				tripRequest.getJourneyTime(),
				collectionOfId.getSourceStop(),
				collectionOfId.getDestStop(),
				collectionOfId.getBus(),
				agency.get());

		Trip savedTrip = tripRepository.save(trip);
		return savedTrip;
	}

	@Override
	public Trip updatingTrip(Long id, TripRequest tripRequest) {

		Optional<Trip> trip = tripRepository.findById(id);

		if (!trip.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found");
		}

		Optional<Agency> agency = agencyRepository.findById(tripRequest.getAgencyId());
		CollectionOfId collectionOfId = checkIfIdExist(agency, tripRequest);

		trip.get().setFare(tripRequest.getFare());
		trip.get().setJourneyTime(tripRequest.getJourneyTime());
		trip.get().setSourceStop(collectionOfId.getSourceStop());
		trip.get().setDestStop(collectionOfId.getDestStop());
		trip.get().setBus(collectionOfId.getBus());
		trip.get().setAgency(agency.get());

		Trip updatedTrip = tripRepository.save(trip.get());
		return updatedTrip;
	}
}
