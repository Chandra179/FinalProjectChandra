package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetTripByJourneyTimeRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	private int minJourneyTime;

	private int maxJourneyTime;
}