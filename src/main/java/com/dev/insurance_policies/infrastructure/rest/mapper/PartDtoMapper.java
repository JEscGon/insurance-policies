package com.dev.insurance_policies.infrastructure.rest.mapper;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.generated.controller.model.PartDto;
import com.dev.insurance_policies.generated.controller.model.ThirdPartyUserDto;
import com.dev.insurance_policies.generated.controller.model.ThirdPartyVehicleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PartDtoMapper {

    @Mapping(source = "accidentDate", target = "accidentDate", qualifiedByName = "mapToOffsetDateTime")
    @Mapping(source = "dateOfRegistration", target = "dateOfRegistration", qualifiedByName = "mapToOffsetDateTime")
    @Mapping(source = "dateOfLastUpdate", target = "dateOfLastUpdate", qualifiedByName = "mapToOffsetDateTime")
    PartDto fromDomainToDto(Part part);

    @Mapping(source = "accidentDate", target = "accidentDate", qualifiedByName = "mapToLocalDateTime")
    @Mapping(source = "dateOfRegistration", target = "dateOfRegistration", qualifiedByName = "mapToLocalDateTime")
    @Mapping(source = "dateOfLastUpdate", target = "dateOfLastUpdate", qualifiedByName = "mapToLocalDateTime")
    Part fromDtoToDomain(PartDto partDto);

    void updatePartFromExisting(@MappingTarget PartDto target, PartDto source);

    @Named("mapToOffsetDateTime")
    default OffsetDateTime mapToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }

    @Named("mapToLocalDateTime")
    default LocalDateTime mapToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }


}

