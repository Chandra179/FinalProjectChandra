package com.chandra.bus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.payload.request.BusRequest;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;

@Component
public class BusServiceImpl implements BusService {

	@Autowired
	private AgencyRepository agencyRepository;
	
	@Autowired
	private BusRepository busRepository;

	@Override
	public Bus addNewBus(BusRequest busRequest) {
		Agency agency = agencyRepository.findById(busRequest.getAgencyId()).get();

		Bus bus = new Bus(
				busRequest.getCode(),
				busRequest.getCapacity(),
				busRequest.getMake(),
				agency);

		Bus savedBus = busRepository.save(bus);
		return savedBus;
	}

	@Override
	public Bus updatingBus(Long id, BusRequest busRequest) {
		Bus bus = busRepository.findById(id).get();

		Agency agency = agencyRepository.findById(busRequest.getAgencyId()).get();

		bus.setCode(busRequest.getCode());
		bus.setCapacity(busRequest.getCapacity());
		bus.setMake(busRequest.getMake());
		bus.setAgency(agency);

		Bus savedBus = busRepository.save(bus);
		return savedBus;
	}

}
