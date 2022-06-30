package com.chandra.bus.model.bus;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.chandra.bus.model.user.User;

import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "tb_agency")
public class Agency {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    private User owner;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private Set<Bus> buses;

	public Agency(String code, String name, String details, User owner) {
		this.code = code;
		this.name = name;
		this.details = details;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
