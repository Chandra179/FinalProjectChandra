package com.chandra.bus.payload.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StopRequest {
	@NotNull
	private Integer source_stop;

	@NotNull
	private Integer dest_stop;
}
