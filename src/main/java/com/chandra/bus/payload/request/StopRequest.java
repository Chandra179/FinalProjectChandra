package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StopRequest {
	@NotBlank
	private String code;

	@NotBlank
	private String name;

	@NotBlank
	private String detail;
}
