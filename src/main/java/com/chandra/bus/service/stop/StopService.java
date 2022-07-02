package com.chandra.bus.service.stop;

import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.payload.request.StopRequest;

public interface StopService {

	Stop addNewStop(StopRequest stopReq);

	Stop updatingStop(Long id, StopRequest stopReq);
}
