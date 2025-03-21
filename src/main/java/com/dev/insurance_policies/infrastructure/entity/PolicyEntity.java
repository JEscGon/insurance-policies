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
    private Long userId;
    private Long vehicleId;
    private Long benefitaryId;
    private String iban;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "policy_type_id")
    private PolicyTypeEntity policyType;

    private Boolean active;
    private Long premiumAmount;

    @OneToMany(mappedBy = "policy")
    private List<PartEntity> parts;


}
