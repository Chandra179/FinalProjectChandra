package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	@NotNull
	private int seatNumber;

	@NotNull
	private Boolean cancellable;

	@NotBlank
	private String journeyDate;

	@NotNull
	private Long passegerId;

	@NotNull
	private Long tripScheduleId;
}