package com.dev.insurance_policies.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {

    private Long id;

    private Long policyId;
    private Long thirdPartyId;
    private Long thirdPartyVehicleId;
    private Long stateId;

    private String placeEvent;
    private String description;

    private LocalDateTime accidentDate;
    private LocalDateTime dateOfRegistration;
    private LocalDateTime dateOfLastUpdate;
}
