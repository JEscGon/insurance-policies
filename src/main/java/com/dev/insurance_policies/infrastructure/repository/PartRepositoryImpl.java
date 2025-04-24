package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.application.repository.PartRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.PartJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.PartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PartRepositoryImpl implements PartRepository {

    private final PartMapper partMapper;
    private final PartJpaRepository partJpaRepository;

    @Override     //TODO : FIX ID PROBLEM
    public void save(Part part) {
        var partEntity = partMapper.fromDomainToEntity(part);
        if(part.getId() == null){
            partEntity.setDateOfRegistration(LocalDateTime.now());
        } else {
            Optional<PartEntity> partEntityOptional = partJpaRepository.findById(part.getId());
            if(partEntityOptional.isPresent()){
                var existingPart = partEntityOptional.get();
                partEntity.setDateOfRegistration(existingPart.getDateOfRegistration());
                partEntity.setDateOfLastUpdate(LocalDateTime.now());
                partMapper.updatePartFromExisting(existingPart, partEntity);
            }
        }
        partJpaRepository.save(partEntity);
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
