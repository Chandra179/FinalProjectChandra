package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;

public class TripRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	private int fare;

	private int journeyTime;

	private Long sourceStopId;

	private Long destStopId;

	private Long busId;

	private Long agencyId;
}