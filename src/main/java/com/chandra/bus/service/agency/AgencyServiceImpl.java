package com.chandra.bus.service.agency;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.model.user.User;
import com.chandra.bus.payload.request.AgencyRequest;
import com.chandra.bus.repository.AgencyRepository;
import com.chandra.bus.repository.UserRepository;

/**
 * Class untuk handling Agency
 * 
 * @since 1.0
 */
@Component
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private AgencyRepository agencyRepository;
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * Method untuk menambahakan Agency
	 * 
	 * @param agencyRequest payload AgencyRequest
	 * @return model Agency
	 */
	@Override
	public Agency addNewAgency(AgencyRequest agencyRequest) {
		
		Optional<User> user = userRepository.findById(agencyRequest.getOwner());

		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}

		try {
			Agency agency = new Agency(
					agencyRequest.getCode(),
					agencyRequest.getDetails(),
					agencyRequest.getName(),
					user.get());

			Agency savedAgency = agencyRepository.save(agency);
			return savedAgency;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

	/**
	 * Method untuk update Agency
	 * 
	 * @param id ID agency
	 * @param agencyRequest payload AgencyRequest
	 * @return model Agency
	 */
	@Override
	public Agency updatingAgency(Long id, AgencyRequest agencyRequest) {

		Optional<Agency> agency = agencyRepository.findById(id);
		Optional<User> user = userRepository.findById(agencyRequest.getOwner());

		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}

		if (!agency.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agency not found");
		}

		try {
			agency.get().setCode(agencyRequest.getCode());
			agency.get().setDetails(agencyRequest.getDetails());
			agency.get().setName(agencyRequest.getName());
			agency.get().setOwner(user.get());

			Agency updatedAgency = agencyRepository.save(agency.get());
			return updatedAgency;

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
		}
	}

}
