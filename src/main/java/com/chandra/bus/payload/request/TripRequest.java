package com.chandra.bus.payload.request;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TripRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	@NotNull
	private Integer fare;

	@NotNull
	private Integer journeyTime;

	@NotNull
	private Long sourceStopId;

	@NotNull
	private Long destStopId;

	@NotNull
	private Long busId;

	@NotNull
	private Long agencyId;
}