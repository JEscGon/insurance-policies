package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.infrastructure.entity.PolicyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PolicyMapper {

    Policy fromEntityToDomain(PolicyEntity policyEntity);
    PolicyEntity fromDomainToEntity(Policy policy);
    void updatePolicyFromExisting(@MappingTarget PolicyEntity target, PolicyEntity source);

}
