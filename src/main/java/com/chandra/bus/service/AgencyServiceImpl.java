package com.chandra.bus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.user.User;
import com.chandra.bus.payload.request.AgencyRequest;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.UserRepository;

@Component
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private AgencyRepository agencyRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Agency addNewAgency(AgencyRequest agencyRequest) {
		User user = userRepository.findById(agencyRequest.getOwner()).get();
		Agency agency = new Agency(
				agencyRequest.getCode(),
				agencyRequest.getDetails(),
				agencyRequest.getName(),
				user);

		Agency savedAgency = agencyRepository.save(agency);
		return savedAgency;
	}

	@Override
	public Agency updatingAgency(Long id, AgencyRequest agencyDetail) {
		Agency agency = agencyRepository.findById(id).get();
		User user = userRepository.findById(agencyDetail.getOwner()).get();

		agency.setCode(agencyDetail.getCode());
		agency.setDetails(agencyDetail.getDetails());
		agency.setName(agencyDetail.getName());
		agency.setOwner(user);

		Agency updatedAgency = agencyRepository.save(agency);
		return updatedAgency;
	}

}
