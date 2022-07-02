package com.chandra.bus.model.bus;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_bus", uniqueConstraints = { @UniqueConstraint(columnNames = { "code" }) })
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private Integer capacity;

	private String make;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	private Agency agency;

	public Bus(String code, int capacity, String make, Agency agency) {
		this.code = code;
		this.capacity = capacity;
		this.make = make;
		this.agency = agency;
	}
}
