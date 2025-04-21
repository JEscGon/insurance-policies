package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.State;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StateMapper {

    State fromEntityToDomain(StateEntity stateEntity);
    StateEntity fromDomainToEntity(State state);

}
