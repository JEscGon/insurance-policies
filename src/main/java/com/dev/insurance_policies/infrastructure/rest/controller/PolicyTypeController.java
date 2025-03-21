package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.service.PolicyTypeService;
import com.dev.insurance_policies.infrastructure.rest.controller.mapper.PolicyTypeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PolicyTypeController  {

    private final PolicyTypeService policyTypeService;
    private final PolicyTypeDtoMapper policyTypeDtoMapper;


}
