package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.application.repository.PartRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.PartThirdPartyJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.PartThirdPartyVehicleJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.StateJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.PartJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartThirdPartyEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartThirdPartyVehicleEntity;
import com.dev.insurance_users.generated.client.api.ThirdUsersApi;
import com.dev.insurance_users.generated.client.api.ThirdVehiclesApi;
import com.dev.insurance_policies.infrastructure.repository.mapper.PartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PartRepositoryImpl implements PartRepository {

    private final PartMapper partMapper;
    private final PartJpaRepository partJpaRepository;
    private final StateJpaRepository stateJpaRepository;

    //Asociacion con PartThirdParty y PartThirdPartyVehicle
    private final ThirdUsersApi thirdUsersApi;
    private final ThirdVehiclesApi thirdVehiclesApi;
    private final PartThirdPartyVehicleJpaRepository partThirdPartyVehicleJpaRepository;
    private final PartThirdPartyJpaRepository partThirdPartyJpaRepository;

    @Override
    @Transactional
    public void save(Part part) {

        var partEntity = partMapper.fromDomainToEntity(part);

        if (part.getId() == null) { // CREAR
            partEntity.setDateOfRegistration(LocalDateTime.now());

            partEntity.setState(stateJpaRepository.findById(1L)
                    //TODO: excepcion personalizada ResorceNotFoundException
                    .orElseThrow(() -> new IllegalArgumentException("State not found")));

        } else { // ACTUALIZAR
            Optional<PartEntity> partEntityOptional = partJpaRepository.findById(part.getId());
            if (partEntityOptional.isPresent()) {
                var existingPart = partEntityOptional.get();
                partEntity.setDateOfRegistration(existingPart.getDateOfRegistration());
                partEntity.setDateOfLastUpdate(LocalDateTime.now());
                //TODO: excepcion personalizada ResorceNotFoundException
                partEntity.setState(stateJpaRepository.findById(existingPart.getState().getId()).orElseThrow(() -> new IllegalArgumentException("State not found")));
                partMapper.updatePartFromExisting(existingPart, partEntity);
            }
        }

        partJpaRepository.save(partEntity);

        part.getThirdPartyIds().forEach(thirdPartyId -> {
            var thirdPartyEntity = new PartThirdPartyEntity();
            thirdPartyEntity.setPart(partEntity);
            thirdPartyEntity.setThirdPartyId(thirdPartyId);
            partThirdPartyJpaRepository.save(thirdPartyEntity);
        });

        part.getThirdPartyVehicleIds().forEach(thirdPartyVehicleId -> {
            var thirdPartyVehicleEntity = new PartThirdPartyVehicleEntity();
            thirdPartyVehicleEntity.setPart(partEntity);
            thirdPartyVehicleEntity.setThirdPartyVehicleId(thirdPartyVehicleId);
            partThirdPartyVehicleJpaRepository.save(thirdPartyVehicleEntity);
        });
    }

    @Override
    public Optional<Part> findById(Long id) {
        return partJpaRepository.findById(id)
                .map(partMapper::fromEntityToDomain);
    }

    @Override
    public void deleteById(Long id) {
        partJpaRepository.deleteById(id);
    }

    @Override
    public List<Part> findAll() {
        return partJpaRepository.findAll().stream()
                .map(partMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Part> findAllByPolicyId(String policyId) {
        return partJpaRepository.findAllByPolicyId(Long.valueOf(policyId)).stream()
                .map(partMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return partJpaRepository.existsById(id);
    }


}
