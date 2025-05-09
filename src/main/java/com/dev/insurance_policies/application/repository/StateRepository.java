package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.State;

import java.util.List;
import java.util.Optional;

public interface StateRepository {

    Optional<State> findById(Long id);
    void deleteById(Long id);
    List<State> findAll();
}
