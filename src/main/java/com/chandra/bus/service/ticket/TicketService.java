package com.chandra.bus.service.ticket;

import java.text.ParseException;

import com.chandra.bus.model.bus.Ticket;
import com.chandra.bus.payload.request.TicketRequest;

public interface TicketService {
	Ticket bookingTicket(TicketRequest ticketRequest) throws ParseException;

	Ticket updatingTicket(Long id, TicketRequest ticketRequest) throws ParseException;
}
