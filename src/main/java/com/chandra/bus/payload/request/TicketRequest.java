package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class TicketRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	@NotBlank
	private String journeyDate;

	@NotNull
	private Long tripScheduleId;
}