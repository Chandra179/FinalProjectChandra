package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	@NotBlank
	private String code;

	@NotNull
	private int capacity;

	@NotBlank
	private String make;

	@NotNull
	private Long agencyId;
}
