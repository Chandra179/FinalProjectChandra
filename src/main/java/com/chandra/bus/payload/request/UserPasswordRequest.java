package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPasswordRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	private String password;
}