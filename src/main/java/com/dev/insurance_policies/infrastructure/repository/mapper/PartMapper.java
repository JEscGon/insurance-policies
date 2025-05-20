package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PartMapper {

    @Mapping(source = "policy.id", target = "policyId")
    @Mapping(source = "state.id", target = "stateId")
    @Mapping(target = "thirdPartyIds", ignore = true)
    @Mapping(target = "thirdPartyVehicleIds", ignore = true)
    Part fromEntityToDomain(PartEntity partEntity);

    @Mapping(source = "stateId", target = "state.id")
    @Mapping(source = "policyId", target = "policy.id")
    @Mapping(target = "thirdPartyIds", ignore = true)
    @Mapping(target = "thirdPartyVehicleIds", ignore = true)
    PartEntity fromDomainToEntity(Part part);

    void updatePartFromExisting(@MappingTarget PartEntity target, PartEntity source);





}