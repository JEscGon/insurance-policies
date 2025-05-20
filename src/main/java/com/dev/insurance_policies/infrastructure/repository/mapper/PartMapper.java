package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PartMapper {

    @Mapping(source = "policy.id", target = "policyId")
    @Mapping(source = "state.id", target = "stateId")
    Part fromEntityToDomain(PartEntity partEntity);

    @Mapping(source = "stateId", target = "state.id")
    @Mapping(source = "policyId", target = "policy.id")
    PartEntity fromDomainToEntity(Part part);

    void updatePartFromExisting(@MappingTarget PartEntity target, PartEntity source);

}
