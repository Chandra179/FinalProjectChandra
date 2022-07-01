package com.chandra.bus.payload.request;

import lombok.Data;

@Data
public class GetTripByStopRequest {
	private Long sourceStopId;

	private Long destStopId;
}
