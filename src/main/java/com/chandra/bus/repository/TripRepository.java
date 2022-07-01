package com.chandra.bus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chandra.bus.model.bus.Bus;
import com.chandra.bus.model.bus.Stop;
import com.chandra.bus.model.bus.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
	List<Trip> findAllBySourceStopAndDestStop(Stop sourceStop, Stop destStop);

	List<Trip> findByFare(Integer fare);

	List<Trip> findByJourneyTime(String journeyTime);

	List<Trip> findBySourceStop(String sourceStop);

	List<Trip> findByDestStop(String destStop);

	List<Trip> findByBus(Optional<Bus> tes);

	List<Trip> findByAgency(String agency);

	@Query(value = "SELECT DISTINCT * FROM tb_trip WHERE source_stop_id = :sourceStopId AND dest_stop_id = :destStopId", nativeQuery = true)
	List<Trip> findTripsByStops(Long sourceStopId, Long destStopId);

	@Query(value = "SELECT * FROM tb_trip WHERE agency_id = :id", nativeQuery = true)
	List<Trip> findByAgencyId(Long id);
}