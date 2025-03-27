package com.dev.insurance_policies.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    //    @ManyToOne
    @Column(name = "third_party_id")
    private Long thirdPartyId;
    //    @ManyToOne
    @Column(name = "third_party_vehicle_id")
    private Long thirdPartyVehicleId;

    private String placeEvent;
    private String description;

    private LocalDateTime accidentDate;
    private LocalDateTime dateOfRegistration;
    private LocalDateTime dateOfLastUpdate;

}
