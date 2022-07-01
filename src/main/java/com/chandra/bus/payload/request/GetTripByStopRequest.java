package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class GetTripByStopRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	private Integer sourceStopId;

	private Integer destStopId;
}
