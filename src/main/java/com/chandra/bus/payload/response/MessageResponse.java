package com.chandra.bus.payload.response;

import java.util.List;

import com.chandra.bus.model.bus.Agency;
import com.chandra.bus.payload.request.AgencyRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse<T> {

	private String message;
	private Boolean success;
	private List<T> data;
	private T object;

	public MessageResponse(String message) {
		this.message = message;
	}

	public MessageResponse(Boolean success, String message, List<T> data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public MessageResponse(Boolean success, String message, T object) {
		this.success = success;
		this.message = message;
		this.object = object;
	}

	public MessageResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
		this.data = null;
	}
}
