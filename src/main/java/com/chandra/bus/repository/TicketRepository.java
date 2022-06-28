package com.chandra.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chandra.bus.model.bus.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
