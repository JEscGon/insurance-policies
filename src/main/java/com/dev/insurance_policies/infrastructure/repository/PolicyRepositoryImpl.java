package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.repository.PolicyRepository;
import com.dev.insurance_policies.infrastructure.entity.PolicyEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyTypeJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.PolicyMapper;
import com.dev.insurance_users.generated.client.api.UsersApi;
import com.dev.insurance_users.generated.client.api.VehiclesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PolicyRepositoryImpl implements PolicyRepository {

    private final PolicyMapper policyMapper;
    private final PolicyJpaRepository policyJpaRepository;
    private final PolicyTypeJpaRepository policyTypeJpaRepository;


    @Override
    public void save(Policy policy) {
        var aux2 = policy.getPolicyTypeId();
        var policyEntity = policyMapper.fromDomainToEntity(policy);
        if (policy.getId() != null) {
            policyJpaRepository.findById(policy.getId()).ifPresent(existingPolicy -> {
                policyEntity.setRegistrationDate(existingPolicy.getRegistrationDate());
                policyEntity.setLastUpdateDate(LocalDate.now());
                policyEntity.setPolicyType(policyTypeJpaRepository.findById(aux2).get());
                policyMapper.updatePolicyFromExisting(existingPolicy, policyEntity);
            });
        } else {
            policyEntity.setRegistrationDate(LocalDate.now());
        }
        policyJpaRepository.save(policyEntity);
    }

    @Override
    public Optional<Policy> findById(Long id) {
            return policyJpaRepository.findById(id)
                    .map(policyMapper::fromEntityToDomain);
    }

    @Override
    public List<Policy> findAll() {
        return policyJpaRepository.findAll().stream()
                .map(policyMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        policyJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Policy> findByMatricula(String matricula) {
        return null;
    }

    @Override
    public Optional<Policy> findByUserId(Long userId) {
        return policyJpaRepository.findByUserId(userId)
                .map(policyMapper::fromEntityToDomain);
    }


}
