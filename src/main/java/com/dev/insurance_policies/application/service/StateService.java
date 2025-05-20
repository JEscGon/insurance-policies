package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.State;
import com.dev.insurance_policies.application.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    public Optional<State> findById(Long id) {
        return stateRepository.findById(id);
    }

    public void deleteStateById(Long id) {
        stateRepository.deleteById(id);
    }

    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

}

