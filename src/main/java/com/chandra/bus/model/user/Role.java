package com.chandra.bus.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
	@Column(length = 20)
	private UserRoles name;

	public Role(UserRoles name) {
		this.name = name;
	}
}
