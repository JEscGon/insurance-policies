package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.domain.State;
import com.dev.insurance_policies.application.service.StateService;
import com.dev.insurance_policies.generated.controller.model.StateDto;
import com.dev.insurance_policies.infrastructure.rest.mapper.StateDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {

    private final StateService stateService;
    private final StateDtoMapper stateDtoMapper;

    @GetMapping
    public ResponseEntity<List<StateDto>> getAllStates() {
        List<State> states = stateService.getAllStates();
        List<StateDto> dtos = states.stream()
                .map(stateDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDto> getStateById(@PathVariable Long id) {
        return stateService.findById(id)
                .map(state -> new ResponseEntity<>(
                        stateDtoMapper.fromDomainToDto(state),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
