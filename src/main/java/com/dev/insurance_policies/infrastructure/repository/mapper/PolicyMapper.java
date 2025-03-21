package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.infrastructure.entity.PolicyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {StateMapper.class,PolicyTypeMapper.class, PartMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PolicyMapper {

    @Mapping(source = "policyType.id", target = "policyType")
    Policy fromEntityToDomain(PolicyEntity policyEntity);

    @Mapping(source = "policyType", target = "policyType.id")
    PolicyEntity fromDomainToEntity(Policy policy);
    void updatePolicyFromExisting(@MappingTarget PolicyEntity target, PolicyEntity source);

}
