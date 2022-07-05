package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPasswordRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	@NotBlank
	private String password;
}