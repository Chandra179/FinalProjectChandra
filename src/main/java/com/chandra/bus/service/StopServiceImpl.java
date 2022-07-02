package com.chandra.bus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.payload.request.StopRequest;
import com.chandra.bus.repository.StopRepository;

@Component
public class StopServiceImpl implements StopService {

	@Autowired
	StopRepository stopRepository;

	@Override
	public Stop addNewStop(StopRequest stopReq) {

		Stop stop = new Stop(stopReq.getCode(), stopReq.getName(), stopReq.getDetail());
		Stop savedStop = stopRepository.save(stop);
		return savedStop;
	}

	@Override
	public Stop updatingStop(Long id, StopRequest stopReq) {

		Stop stop = stopRepository.findById(id).get();
		stop.setCode(stopReq.getCode());
		stop.setName(stopReq.getName());
		stop.setDetail(stopReq.getDetail());

		Stop savedStop = stopRepository.save(stop);
		return savedStop;
	}

}
