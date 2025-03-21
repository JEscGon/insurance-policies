package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.Policy;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository {

    public void save(Policy policy);
    public Optional<Policy> findById(Long id);
    public List<Policy> findAll();
    public void deleteById(Long id);

}
