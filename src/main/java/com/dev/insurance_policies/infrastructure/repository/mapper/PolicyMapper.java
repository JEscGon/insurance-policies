package com.dev.insurance_policies.infrastructure.repository.mapper;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartThirdPartyEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartThirdPartyVehicleEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PolicyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PolicyMapper {

    @Mapping(target = "policyTypeId", source = "policyType.id")
    Policy fromEntityToDomain(PolicyEntity policyEntity);

    @Mapping(target = "policyType.id", source = "policyTypeId")
    PolicyEntity fromDomainToEntity(Policy policy);

    void updatePolicyFromExisting(@MappingTarget PolicyEntity target, PolicyEntity source);

    // Métodos de mapeo personalizados
    default List<Long> mapPartThirdPartyEntitiesToIds(List<PartThirdPartyEntity> entities) {
        return entities == null ? null : entities.stream()
                .map(PartThirdPartyEntity::getThirdPartyId)
                .collect(Collectors.toList());
    }

    default List<PartThirdPartyEntity> mapIdsToPartThirdPartyEntities(List<Long> ids) {
        return ids == null ? null : ids.stream()
                .map(id -> {
                    PartThirdPartyEntity entity = new PartThirdPartyEntity();
                    entity.setThirdPartyId(id);
                    return entity;
                })
                .collect(Collectors.toList());
    }

    default List<Long> mapPartThirdPartyVehicleEntitiesToIds(List<PartThirdPartyVehicleEntity> entities) {
        return entities == null ? null : entities.stream()
                .map(PartThirdPartyVehicleEntity::getThirdPartyVehicleId)
                .collect(Collectors.toList());
    }

    default List<PartThirdPartyVehicleEntity> mapIdsToPartThirdPartyVehicleEntities(List<Long> ids) {
        return ids == null ? null : ids.stream()
                .map(id -> {
                    PartThirdPartyVehicleEntity entity = new PartThirdPartyVehicleEntity();
                    entity.setThirdPartyVehicleId(id);
                    return entity;
                })
                .collect(Collectors.toList());
    }
}