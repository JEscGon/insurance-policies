package com.dev.insurance_policies.infrastructure.rest.mapper;

import com.dev.insurance_policies.application.domain.PolicyType;
import com.dev.insurance_policies.generated.controller.model.PolicyTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PolicyTypeDtoMapper {

    PolicyType fromDtoToDomain(PolicyTypeDto policyTypeDto);

    PolicyTypeDto fromDomainToDto(PolicyType policyType);

}
