package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class UserRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	private String firstName;

	private String lastName;

	private String mobileNumber;
}