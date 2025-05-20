package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PolicyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PolicyMapper {

    @Mapping(target = "policyTypeId", source = "policyType.id")
    Policy fromEntityToDomain(PolicyEntity policyEntity);

    @Mapping(target = "policyType.id", source = "policyTypeId")
    PolicyEntity fromDomainToEntity(Policy policy);

    void updatePolicyFromExisting(@MappingTarget PolicyEntity target, PolicyEntity source);

}
