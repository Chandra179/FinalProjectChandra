package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPasswordRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	private String password;

	public UserPasswordRequest(String password) {
		this.password = password;
	}
}