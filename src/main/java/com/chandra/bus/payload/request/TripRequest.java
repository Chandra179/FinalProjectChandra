package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TripRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	@NotNull
	private int fare;

	@NotBlank
	private int journeyTime;

	@NotBlank
	private Long sourceStopId;

	@NotBlank
	private Long destStopId;

	@NotBlank
	private Long busId;

	@NotBlank
	private Long agencyId;
}