package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class TripScheduleRequest {

	@NotBlank
	private String tripDate;

	@NotNull
	private int availableSeats;

	private int tripDetail;
}
