package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.PolicyType;
import java.util.List;
import java.util.Optional;

public interface PolicyTypeRepository {

    public List<PolicyType> findAll();
    public Optional<PolicyType> findById(Long id);
    public void save(PolicyType policyType);
    public void deleteById(Long id);

}
