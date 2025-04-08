package com.dev.insurance_policies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    private Long beneficiaryId;

    @ManyToOne
    @JoinColumn(name = "policy_type_id")
    private PolicyTypeEntity policyType;

    @Column(unique = true)
    private String iban;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @CreatedDate
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @LastModifiedDate
    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;

    private Boolean active;

    @Column(name = "premium_amount")
    private Long premiumAmount;

    @OneToMany(mappedBy = "policy")
    private List<PartEntity> parts;

}
