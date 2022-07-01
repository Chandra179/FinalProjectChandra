package com.chandra.bus.payload.request;

import lombok.Data;

@Data
public class GetTripByStopRequest {
	private Integer sourceStopId;
	private Integer destStopId;
}
