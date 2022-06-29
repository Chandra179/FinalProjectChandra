package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BusRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	@NotBlank
	private String code;

	@NotNull
	private int capacity;

	@NotBlank
	private String make;

	@ApiModelProperty(hidden = true)
	private long agencyId;
}