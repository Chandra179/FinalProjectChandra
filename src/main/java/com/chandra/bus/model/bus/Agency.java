package com.chandra.bus.model.bus;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.chandra.bus.model.user.User;

import java.util.Set;

@Data
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
}
