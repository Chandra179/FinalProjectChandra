package com.chandra.bus.service.agency;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.payload.request.AgencyRequest;

public interface AgencyService {

	Agency updatingAgency(Long id, AgencyRequest agencyDetail);

	Agency addNewAgency(AgencyRequest agencyRequest);

}
