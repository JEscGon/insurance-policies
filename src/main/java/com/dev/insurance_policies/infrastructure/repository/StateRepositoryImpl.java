package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.State;
import com.dev.insurance_policies.application.repository.StateRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.StateJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.StateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StateRepositoryImpl implements StateRepository {

    private final StateMapper stateMapper;
    private final StateJpaRepository stateJpaRepository;

    @Override
    public void save(State state) {
    }

    @Override
    public Optional<State> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<State> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
    }
}
