package com.chandra.bus.service.bus;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.payload.request.BusRequest;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Class untuk handling Bus
 * 
 * @since 1.0
 */
@Component
public class BusServiceImpl implements BusService {

	@Autowired
	private AgencyRepository agencyRepository;
	
	@Autowired
	private BusRepository busRepository;

	/**
	 * Method untuk menambahkan Bus
	 * 
	 * @param busRequest payload BusRequest
	 * @return model Bus
	 */
	@Override
	public Bus addNewBus(BusRequest busRequest) {

		Optional<Agency> agency = agencyRepository.findById(busRequest.getAgencyId());

		if (!agency.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No agency found");
		}

		Bus bus = new Bus(
				busRequest.getCode(),
				busRequest.getCapacity(),
				busRequest.getMake(),
				agency.get());

		Bus savedBus = busRepository.save(bus);
		return savedBus;
	}

	/**
	 * Method untuk update Bus
	 * 
	 * @param busRequest payload BusRequest
	 * @return model Bus
	 */
	@Override
	public Bus updatingBus(Long id, BusRequest busRequest) {

		Optional<Agency> agency = agencyRepository.findById(busRequest.getAgencyId());
		Optional<Bus> bus = busRepository.findById(id);

		if (!agency.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No agency found");
		}

		if (!bus.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No bus found");
		}

		bus.get().setCode(busRequest.getCode());
		bus.get().setCapacity(busRequest.getCapacity());
		bus.get().setMake(busRequest.getMake());
		bus.get().setAgency(agency.get());

		Bus savedBus = busRepository.save(bus.get());
		return savedBus;
	}
}
