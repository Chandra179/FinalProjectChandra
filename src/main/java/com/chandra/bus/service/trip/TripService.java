package com.chandra.bus.service.trip;

import org.springframework.stereotype.Component;

import com.chandra.bus.model.bus.Trip;
import com.chandra.bus.payload.request.TripRequest;

@Component
public interface TripService {

	Trip addNewTrip(TripRequest tripRequest);
}
