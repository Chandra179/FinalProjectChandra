package com.chandra.bus.service.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.user.User;
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

		try {
			Agency agency = agencyRepository.findById(busRequest.getAgencyId()).get();

			Bus bus = new Bus(
					busRequest.getCode(),
					busRequest.getCapacity(),
					busRequest.getMake(), 
					agency);

			Bus savedBus = busRepository.save(bus);
			return savedBus;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e.getCause());
		}
	}

	@Override
	public Bus updatingBus(Long id, BusRequest busRequest) {

		try {
			Bus bus = busRepository.findById(id).get();

			Agency agency = agencyRepository.findById(busRequest.getAgencyId()).get();

			bus.setCode(busRequest.getCode());
			bus.setCapacity(busRequest.getCapacity());
			bus.setMake(busRequest.getMake());
			bus.setAgency(agency);

			Bus savedBus = busRepository.save(bus);
			return savedBus;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e.getCause());
		}
	}
}
