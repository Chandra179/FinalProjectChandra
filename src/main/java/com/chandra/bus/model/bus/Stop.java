package com.chandra.bus.model.bus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_stop")
public class Stop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String code;

	@NotBlank
	private String name;

	@NotBlank
	private String detail;

	public Stop(String code, String name, String detail) {
		this.code = code;
		this.name = name;
		this.detail = detail;
	}
}
