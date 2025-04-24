package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Vehicle;
import com.dev.insurance_users.generated.client.model.VehicleClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleDtoClientMapper {
    Vehicle fromDtoToDomain(VehicleClientDto vehicleDto);
    VehicleClientDto fromDomainToDto(Vehicle vehicleDomain);
}
