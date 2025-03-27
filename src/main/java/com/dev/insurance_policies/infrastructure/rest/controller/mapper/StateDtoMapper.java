package com.dev.insurance_policies.infrastructure.rest.controller.mapper;

import com.dev.insurance_policies.application.domain.State;
import com.dev.insurance_policies.generated.controller.model.StateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateDtoMapper {

    State fromDtoToDomain(StateDto stateDto);

    StateDto fromDomainToDto(State state);

}
