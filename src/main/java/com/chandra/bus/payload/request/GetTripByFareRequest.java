package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetTripByFareRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	private Integer minFare;

	private Integer maxFare;
}
