package com.dev.insurance_policies.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {

    private Long id;

    private Long policyId;
    private List<Long> thirdPartyIds;
    private List<Long> thirdPartyVehicleIds;
    private Long stateId;

    private String placeEvent;
    private String description;

    private LocalDateTime accidentDate;
    private LocalDateTime dateOfRegistration;
    private LocalDateTime dateOfLastUpdate;
}
