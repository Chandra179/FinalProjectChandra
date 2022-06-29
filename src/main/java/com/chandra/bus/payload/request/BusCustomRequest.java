package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.chandra.bus.model.user.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCustomRequest {
	
	@ApiModelProperty(hidden = true)
	private Long id;

	@NotBlank
	private String code;

	@NotNull
	private int capacity;

	@NotBlank
	private String make;

	@ApiModelProperty(hidden = true)
	@NotBlank
	private User agencyId;
}
