package com.chandra.bus.service.stop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.payload.request.StopRequest;
import com.chandra.bus.repository.StopRepository;

@Component
public class StopServiceImpl implements StopService {

	@Autowired
	StopRepository stopRepository;

	@Override
	public Stop addNewStop(StopRequest stopReq) {
		try {
			Stop stop = new Stop(stopReq.getCode(), stopReq.getName(), stopReq.getDetail());
			Stop savedStop = stopRepository.save(stop);
			return savedStop;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

	@Override
	public Stop updatingStop(Long id, StopRequest stopReq) {

		try {
			Stop stop = stopRepository.findById(id).get();
			stop.setCode(stopReq.getCode());
			stop.setName(stopReq.getName());
			stop.setDetail(stopReq.getDetail());

			Stop savedStop = stopRepository.save(stop);
			return savedStop;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

}
