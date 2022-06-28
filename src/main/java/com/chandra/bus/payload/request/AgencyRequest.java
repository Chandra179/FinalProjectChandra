package com.chandra.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;

public class AgencyRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	private String code;

	private String name;

	private String details;

	private Long owner;
}