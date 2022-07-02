package com.chandra.bus.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TicketResponse {
	String email;
	Integer seatNumber;
	String journeyDate;
	String busCode;
	Integer Fare;
	Integer journeyTime;
	String sourceStop;
	String destStop;
}
