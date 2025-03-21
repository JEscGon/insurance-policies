package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.State;

import java.util.List;
import java.util.Optional;

public interface StateRepository {

    public void save(State state);
    public Optional<State> findById(Long id);
    public void deleteById(Long id);
    public List<State> findAll();
}
