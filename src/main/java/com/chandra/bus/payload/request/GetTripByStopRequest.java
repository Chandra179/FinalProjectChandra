package com.chandra.bus.payload.request;

import lombok.Data;

@Data
public class GetTripByStopRequest {
	private Long sourceStopid;

	private Long destStopId;
}
