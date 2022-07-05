package com.chandra.bus.model.bus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_trip")
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer fare;

	@NotNull
	private Integer journeyTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_stop_id")
	private Stop sourceStop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dest_stop_id")
	private Stop destStop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bus_id")
	private Bus bus;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	private Agency agency;

	public Trip(int fare, int journeyTime, Stop sourceStop, Stop destStop, Bus bus, Agency agency) {
		this.fare = fare;
		this.journeyTime = journeyTime;
		this.sourceStop = sourceStop;
		this.destStop = destStop;
		this.bus = bus;
		this.agency = agency;
	}
}
