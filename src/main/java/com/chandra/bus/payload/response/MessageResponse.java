package com.chandra.bus.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class MessageResponse<T> {
	private String message;
	private Boolean success;
	private List<T> data;
	private T object;

	public MessageResponse(String message) {
		super();
		this.message = message;
	}
}