package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TripScheduleRequest {

	@NotBlank
	private String tripDate;

	@NotNull
	private Integer availableSeats;

	@NotNull
	private Integer tripDetail;
}
