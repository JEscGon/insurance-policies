package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.service.PolicyTypeService;
import com.dev.insurance_policies.generated.controller.api.PolicyTypesApi;
import com.dev.insurance_policies.infrastructure.rest.controller.mapper.PolicyTypeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PolicyTypeController implements PolicyTypesApi {

    private final PolicyTypeService policyTypeService;
    private final PolicyTypeDtoMapper policyTypeDtoMapper;


}
