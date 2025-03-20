package com.dev.insurance_policies.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private Long benefitaryId;
    private String iban;
    private LocalDate startDate;
    private LocalDate endDate;
    private PolicyType policyTypeEntity;
    private Boolean active;
    private Long premiumAmount;
    private List<Part> partEntities;
}
