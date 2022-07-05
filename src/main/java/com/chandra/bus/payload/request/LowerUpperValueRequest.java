package com.chandra.bus.payload.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LowerUpperValueRequest {
	@ApiModelProperty(hidden = true)
	private Long id;

	@NotNull
	private Integer lowerValue;

	@NotNull
	private Integer upperValue;
}
