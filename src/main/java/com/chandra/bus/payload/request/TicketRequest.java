package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;

public class TicketRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	private int seatNumber;

	private Boolean cancellable;

	private String journeyDate;

	private Long passegerId;

	private Long tripScheduleId;
}