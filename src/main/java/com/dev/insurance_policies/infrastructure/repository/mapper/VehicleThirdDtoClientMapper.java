package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.VehicleThird;
import com.dev.insurance_users.generated.client.model.ThirdPartyVehicleClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleThirdDtoClientMapper {
    VehicleThird fromDtoToDomain(ThirdPartyVehicleClientDto vehicleDto);
    ThirdPartyVehicleClientDto fromDomainToDto(VehicleThird vehicleDomain);
}
