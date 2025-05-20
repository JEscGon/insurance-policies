package com.dev.insurance_policies.infrastructure.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "part_third_party_vehicle")
public class PartThirdPartyVehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    private PartEntity part;

    @Column(name = "third_party_vehicle_id", nullable = false)
    private Long thirdPartyVehicleId;
}