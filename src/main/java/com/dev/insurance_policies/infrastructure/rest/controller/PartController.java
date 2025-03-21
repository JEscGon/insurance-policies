package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.service.PartService;
import com.dev.insurance_policies.generated.controller.api.PartsApi;
import com.dev.insurance_policies.infrastructure.rest.controller.mapper.PartDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartController implements PartsApi {

    private final PartService partService;
    private final PartDtoMapper partDtoMapper;


}
