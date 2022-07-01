package com.chandra.bus.payload.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StopRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	@NotBlank
	private String code;

	@NotBlank
	private String name;

	@NotBlank
	private String detail;
}
