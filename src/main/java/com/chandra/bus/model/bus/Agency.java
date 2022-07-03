package com.chandra.bus.model.bus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.chandra.bus.model.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tb_agency")
public class Agency {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String details;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    private User owner;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private Set<Bus> buses;

	public Agency(String code, String name, String details, User owner) {
		this.code = code;
		this.name = name;
		this.details = details;
		this.owner = owner;
	}
}
