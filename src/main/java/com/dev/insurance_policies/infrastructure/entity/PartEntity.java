package com.dev.insurance_policies.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "parts")
public class PartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_id")
    private Long policyId;

    @Column(name = "third_party_id")
    private Long thirdPartyId;

    @Column(name = "third_party_vehicle_id")
    private Long thirdPartyVehicleId;

    @Column(name = "place_event")
    private String placeEvent;

    private String description;

    //TODO: Descripcion grafica del parte ¿?
    @Column(name = "accident_date")
    private LocalDateTime accidentDate;

    @Column(name = "date_of_registration")
    private LocalDateTime dateOfRegistration;

    @Column(name = "date_of_last_update")
    private LocalDateTime dateOfLastUpdate;

    //TODO: hacer relacion con la tabla de estados
    private StateEntity statePart;

}
