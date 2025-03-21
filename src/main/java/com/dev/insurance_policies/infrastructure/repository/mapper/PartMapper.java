package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.infrastructure.entity.PartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {PolicyMapper.class , StateMapper.class, PolicyTypeMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PartMapper {
    @Mapping(source = "policy.id", target = "policyId")
    @Mapping(source = "state.id", target = "state")
    Part fromEntityToDomain(PartEntity partEntity);

    @Mapping(source = "policyId", target = "policy.id")
    @Mapping(source = "state", target = "state.id")
    PartEntity fromDomainToEntity(Part part);

    void updatePartFromExisting(@MappingTarget PartEntity target, PartEntity source);

}
