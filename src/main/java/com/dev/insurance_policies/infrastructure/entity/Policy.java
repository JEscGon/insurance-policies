package com.dev.insurance_policies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "vehicle_id")
    private Long vehicleId;
    @Column(name = "beneficiary_id")
    private Long benefitaryId;

    @Column(name = "policy_number" , nullable = false)
    private String policyNumber;

    @Column(name = "start_date" , nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date" , nullable = false)
    private LocalDate endDate;

    @Column(name = "coverage_type" , nullable = false)
    private PolicyType coverageType;

    private Boolean active;

    private Long premiumAmount;


}
