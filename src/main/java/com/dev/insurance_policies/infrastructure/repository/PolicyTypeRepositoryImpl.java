package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.PolicyType;
import com.dev.insurance_policies.application.repository.PolicyTypeRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyTypeJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.PolicyTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PolicyTypeRepositoryImpl implements PolicyTypeRepository {

    private final PolicyTypeMapper policyTypeMapper;
    private final PolicyTypeJpaRepository policyTypeJpaRepository;

    @Override
    public List<PolicyType> findAll() {
        return List.of();
    }

    @Override
    public Optional<PolicyType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(PolicyType policyType) {
    }
    @Override
    public void deleteById(Long id) {
    }


}
