package com.chandra.bus.payload.request;

import lombok.Data;

@Data
public class GetTripByFareRequest {
	private Integer minFare;
	private Integer maxFare;
}
