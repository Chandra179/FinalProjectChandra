package com.chandra.bus.service.ticket;

import java.text.ParseException;

import com.chandra.bus.model.bus.TripSchedule;
import com.chandra.bus.payload.request.TicketRequest;

public interface TicketService {
	TripSchedule bookingTicket(TicketRequest ticketRequest) throws ParseException;
}
