package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.domain.PolicyType;
import com.dev.insurance_policies.application.service.PolicyTypeService;
import com.dev.insurance_policies.generated.controller.model.PolicyTypeDto;
import com.dev.insurance_policies.infrastructure.rest.mapper.PolicyTypeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policyTypes")
public class PolicyTypeController {

    private final PolicyTypeService policyTypeService;
    private final PolicyTypeDtoMapper policyTypeDtoMapper;

    @GetMapping
    public ResponseEntity<List<PolicyTypeDto>> getAllPolicyTypes() {
        List<PolicyType> policyTypes = policyTypeService.findAll();
        List<PolicyTypeDto> dtos = policyTypes.stream()
                .map(policyTypeDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyTypeDto> getPolicyTypeById(@PathVariable Long id) {
        return policyTypeService.findById(id)
                .map(policyType -> new ResponseEntity<>(
                        policyTypeDtoMapper.fromDomainToDto(policyType),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Void> savePolicyType(@RequestBody PolicyTypeDto policyTypeDto) {
        PolicyType policyType = policyTypeDtoMapper.fromDtoToDomain(policyTypeDto);
        policyTypeService.save(policyType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicyType(@PathVariable Long id) {
        policyTypeService.deletePolicyTypeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
