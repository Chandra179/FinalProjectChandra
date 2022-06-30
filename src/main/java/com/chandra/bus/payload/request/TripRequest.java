package com.chandra.bus.payload.request;

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