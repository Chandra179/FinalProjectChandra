package com.chandra.bus.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chandra.bus.model.bus.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query(value = "SELECT * FROM tb_ticket t INNER JOIN tb_user u on t.user_id = u.id where u.username = :passenger", nativeQuery = true)
	List<Ticket> findByPassenger(String passenger);

}
