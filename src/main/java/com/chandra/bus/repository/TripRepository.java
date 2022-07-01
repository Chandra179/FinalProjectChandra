package com.chandra.bus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

	List<Trip> findByFareBetween(Integer minFare, Integer maxFare);

	List<Trip> findByJourneyTimeBetween(Integer minJourneyTime, Integer maxJourneyTime);

	List<Trip> findBySourceStop(String sourceStop);

	List<Trip> findByDestStop(String destStop);

	List<Trip> findByBus(Optional<Bus> tes);

	List<Trip> findByAgency(String agency);

	@Query(value = "SELECT DISTINCT * FROM tb_trip WHERE source_stop_id = :sourceStopId AND dest_stop_id = :destStopId", nativeQuery = true)
	List<Trip> findTripsByStops(Integer sourceStopId, Integer destStopId);

	@Query(value = "SELECT * FROM tb_trip WHERE agency_id = :id", nativeQuery = true)
	List<Trip> findByAgencyId(Long id);
}