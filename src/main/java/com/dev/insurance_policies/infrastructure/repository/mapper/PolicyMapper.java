package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.infrastructure.entity.PolicyEntity;
import jakarta.persistence.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PolicyMapper {

    @Mapping(target = "policyTypeId", source = "policyType.id")
    Policy fromEntityToDomain(PolicyEntity policyEntity);

    @Mapping(target = "policyType.id", source = "policyTypeId")
    PolicyEntity fromDomainToEntity(Policy policy);

    void updatePolicyFromExisting(@MappingTarget PolicyEntity target, PolicyEntity source);

}
