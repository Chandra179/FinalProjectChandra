package com.chandra.bus.service;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.payload.request.AgencyRequest;

public interface BusReservationService {

	Agency updatingAgency(Long id, AgencyRequest agencyDetail);

	Agency addNewAgency(AgencyRequest agencyRequest);
}
