package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTripScheduleRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	private int available_seats;

	private Long trip_detail;

	private String trip_date;
}
