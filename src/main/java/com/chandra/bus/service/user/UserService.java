package com.chandra.bus.service.user;

import com.chandra.bus.model.user.User;
import com.chandra.bus.payload.request.SignupRequest;
import com.chandra.bus.payload.request.UserRequest;

public interface UserService {

	String registerNewUser(SignupRequest SignupRequest);

	User updatingUser(Long id, UserRequest userRequest);
}
