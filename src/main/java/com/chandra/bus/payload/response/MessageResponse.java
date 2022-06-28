package com.chandra.bus.payload.response;

import java.util.List;

public class MessageResponse<T> {
	private String message;
	private Boolean success;
	private List<T> data;
	private T object;
}