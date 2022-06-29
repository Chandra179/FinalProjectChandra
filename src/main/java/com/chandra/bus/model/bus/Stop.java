package com.chandra.bus.model.bus;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
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
