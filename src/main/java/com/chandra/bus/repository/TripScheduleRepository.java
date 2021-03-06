package com.chandra.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chandra.bus.model.bus.TripSchedule;

@Repository
public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {
	List<TripSchedule> findAllByTripDate(String tripDate);

	List<TripSchedule> findByTripDate(String tripDate);

	@Query(value = "SELECT DISTINCT * FROM tb_trip_schedule WHERE trip_date = :tripDate", nativeQuery = true)
	List<TripSchedule> findTripScheduleByDate(String tripDate);
}