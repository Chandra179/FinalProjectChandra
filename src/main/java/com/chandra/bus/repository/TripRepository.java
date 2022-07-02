package com.chandra.bus.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

	List<Trip> findByFareBetween(Integer minFare, Integer maxFare);

	List<Trip> findByJourneyTimeBetween(Integer minJourneyTime, Integer maxJourneyTime);

	List<Trip> findByBus(Bus bus);

	@Query(value = "SELECT * FROM tb_trip t INNER JOIN tb_stop s on t.dest_stop_id = s.id WHERE LOWER(s.name) LIKE %:destStop%", nativeQuery = true)
	List<Trip> findByDestStop(String destStop);

	@Query(value = "SELECT * FROM tb_trip t INNER JOIN tb_stop s on t.source_stop_id = s.id WHERE LOWER(s.name) LIKE %:sourceStop%", nativeQuery = true)
	List<Trip> findBySourceStop(String sourceStop);

	@Query(value = "SELECT * FROM tb_trip t INNER JOIN tb_agency s on t.agency_id = s.id WHERE LOWER(s.name) LIKE %:agency%", nativeQuery = true)
	List<Trip> findByAgency(String agency);

	@Query(value = "SELECT DISTINCT * FROM tb_trip WHERE source_stop_id = :sourceStopId AND dest_stop_id = :destStopId", nativeQuery = true)
	List<Trip> findTripsByStops(Integer sourceStopId, Integer destStopId);
}