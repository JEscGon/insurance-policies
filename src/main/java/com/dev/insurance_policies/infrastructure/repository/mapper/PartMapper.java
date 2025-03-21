package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.infrastructure.entity.PartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PartMapper {

    Part fromEntityToDomain(PartEntity partEntity);

    PartEntity fromDomainToEntity(Part part);

    void updatePartFromExisting(@MappingTarget PartEntity target, PartEntity source);

}
