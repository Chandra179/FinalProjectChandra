package com.chandra.bus.payload.request;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class TripRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	@NotNull
	private int fare;

	@NotNull
	private int journeyTime;

	@NotNull
	private Long sourceStopId;

	@NotNull
	private Long destStopId;

	@NotNull
	private Long busId;

	@NotNull
	private Long agencyId;
}