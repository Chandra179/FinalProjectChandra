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

	List<Trip> findByStopBetween(Integer sourceStopId, Integer destStopId);

	List<Trip> findByBus(Optional<Bus> bus);

	List<Trip> findByDestStop(String destStop);

	List<Trip> findBySourceStop(String sourceStop);

	List<Trip> findByAgency(String agency);
}