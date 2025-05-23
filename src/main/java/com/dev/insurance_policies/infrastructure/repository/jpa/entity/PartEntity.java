package com.dev.insurance_policies.infrastructure.repository.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "parts")
public class PartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private PolicyEntity policy;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private StateEntity state;

    @Transient
    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartThirdPartyEntity> thirdPartyIds;
    @Transient
    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartThirdPartyVehicleEntity> thirdPartyVehicleIds;

    private String placeEvent;
    private String description;

    private LocalDateTime accidentDate;
    @CreatedDate
    private LocalDateTime dateOfRegistration;
    @LastModifiedDate
    private LocalDateTime dateOfLastUpdate;


}
