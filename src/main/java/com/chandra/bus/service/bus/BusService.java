package com.chandra.bus.service.bus;

import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.payload.request.BusRequest;

public interface BusService {

	Bus addNewBus(BusRequest busRequest);

	Bus updatingBus(Long id, BusRequest busRequest);
}
