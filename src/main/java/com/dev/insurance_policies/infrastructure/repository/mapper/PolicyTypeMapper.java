package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.PolicyType;
import com.dev.insurance_policies.infrastructure.entity.PolicyTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PolicyTypeMapper {

    PolicyType fromEntityToDomain(PolicyTypeEntity policyTypeEntity);
    PolicyTypeEntity fromDomainToEntity(PolicyType policyType);

}
