package com.dev.insurance_policies.application.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartThirdPartyVehicle {

    private Long id;
    private Part part;
    private Long thirdPartyVehicleId;

}
