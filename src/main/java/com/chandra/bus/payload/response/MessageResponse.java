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

	public MessageResponse(boolean b, String result) {
		// TODO Auto-generated constructor stub
	}

	public MessageResponse(boolean b, String string, Agency updatedAgency) {
		// TODO Auto-generated constructor stub
	}

	public MessageResponse(boolean b, String string, AgencyRequest dataResult) {
		// TODO Auto-generated constructor stub
	}

	public MessageResponse(boolean b, String string, List<AgencyRequest> dataArrResult) {
		// TODO Auto-generated constructor stub
	}
}
