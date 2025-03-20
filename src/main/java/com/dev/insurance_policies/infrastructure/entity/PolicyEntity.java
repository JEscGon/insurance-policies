package com.dev.insurance_policies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "policies")
public class PolicyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "vehicle_id")
    private Long vehicleId;
    @Column(name = "beneficiary_id")
    private Long benefitaryId;

    @Column(name = "iban" , nullable = false)
    private String iban;

    @Column(name = "start_date" , nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date" , nullable = false)
    private LocalDate endDate;

    private PolicyTypeEntity policyTypeEntity;

    private Boolean active;

    private Long premiumAmount;

    @OneToMany
    private List<PartEntity> partEntities;


}
