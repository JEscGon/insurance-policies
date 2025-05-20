package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.service.StateService;
import com.dev.insurance_policies.infrastructure.rest.mapper.StateDtoMapper;
import com.dev.insurance_policies.generated.controller.api.StatesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class StateController implements StatesApi {

    private final StateService stateService;
    private final StateDtoMapper stateDtoMapper;
}
