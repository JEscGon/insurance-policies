package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.PolicyType;
import com.dev.insurance_policies.application.repository.PolicyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyTypeService {

    private final PolicyTypeRepository policyTypeRepository;

    public void save(PolicyType policyType) {
        policyTypeRepository.save(policyType);
    }

    public Optional<PolicyType> findById(Long id) {
        return policyTypeRepository.findById(id);
    }

    public void deletePolicyTypeById(Long id) {
        policyTypeRepository.deleteById(id);
    }


}
