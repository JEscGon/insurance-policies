package com.dev.insurance_policies.infrastructure.rest.mapper;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.generated.controller.model.PolicyDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface PolicyDtoMapper {

    Policy fromDtoToDomain(PolicyDto policyDto);

    PolicyDto fromDomainToDto(Policy policy);

    default LocalDateTime map(OffsetDateTime value) {
        return value == null ? null : value.toLocalDateTime();
    }

    default OffsetDateTime map(LocalDateTime value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

}
