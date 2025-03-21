package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;

    public void save(Policy policy) {
        policyRepository.save(policy);
    }

    public Optional<Policy> findById(Long id) {
        return policyRepository.findById(id);
    }

    public void deletePolicyById(Long id) {
        policyRepository.deleteById(id);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
}
