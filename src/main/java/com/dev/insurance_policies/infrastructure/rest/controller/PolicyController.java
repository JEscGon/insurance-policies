package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.service.PolicyService;
import com.dev.insurance_policies.generated.controller.api.PoliciesApi;
import com.dev.insurance_policies.infrastructure.rest.controller.mapper.PolicyDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PolicyController implements PoliciesApi {

    private final PolicyService policyService;
    private final PolicyDtoMapper policyDtoMapper;

}
