package com.chandra.bus.service.stop;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.payload.request.StopRequest;
import com.chandra.bus.repository.StopRepository;

/**
 * Class untuk handling Bus Stop
 * 
 * @since 1.0
 */
@Component
public class StopServiceImpl implements StopService {

	@Autowired
	StopRepository stopRepository;

	/**
	 * Method untuk melakukan pembuatan Bus Stop
	 * 
	 * @param stopReq payload StopRequest
	 * @return model Stop
	 */
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

	/**
	 * Method untuk melakukan updating Bus Stop
	 * 
	 * @param id ID stop
	 * @param stopReq payload StopRequest
	 * @return model Stop
	 */
	@Override
	public Stop updatingStop(Long id, StopRequest stopReq) {

		Optional<Stop> stop = stopRepository.findById(id);

		if (!stop.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stop found");
		}

		try {
			stop.get().setCode(stopReq.getCode());
			stop.get().setName(stopReq.getName());
			stop.get().setDetail(stopReq.getDetail());

			Stop savedStop = stopRepository.save(stop.get());
			return savedStop;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

}
