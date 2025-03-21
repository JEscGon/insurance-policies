package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.repository.PolicyRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.PolicyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PolicyRepositoryImpl implements PolicyRepository {

    private final PolicyMapper policyMapper;
    private final PolicyJpaRepository policyJpaRepository;

    @Override
    public void save(Policy policy) {

    }

    @Override
    public Optional<Policy> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Policy> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
