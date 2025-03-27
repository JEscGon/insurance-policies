package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.service.PolicyService;
import com.dev.insurance_policies.generated.controller.api.PoliciesApi;
import com.dev.insurance_policies.generated.controller.model.PolicyDto;
import com.dev.insurance_policies.infrastructure.rest.controller.mapper.PolicyDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PolicyController implements PoliciesApi {

    private final PolicyService policyService;
    private final PolicyDtoMapper policyDtoMapper;

    @Override
    public ResponseEntity<Void> savePolicy(PolicyDto policyDto) {
        Policy policy = policyDtoMapper.fromDtoToDomain(policyDto);
        policyService.save(policy);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deletePolicyById(String id) {
        policyService.deletePolicyById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<PolicyDto>> getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        List<PolicyDto> policyDtos = policies.stream()
                .map(policyDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(policyDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PolicyDto> getPolicyById(String id) {
        try {
            Optional<Policy> policyOptional = policyService.findById(Long.parseLong(id));
            return policyOptional
                    .map(policy -> new ResponseEntity<>(policyDtoMapper.fromDomainToDto(policy), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<Void> updatePolicy(String id, PolicyDto policyDto) {
        try {
            Long policyId = Long.parseLong(id);
            policyDto.setId(policyId.intValue());
            Policy policy = policyDtoMapper.fromDtoToDomain(policyDto);
            policyService.save(policy);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<PolicyDto> getPolicyByMatricula(String matricula) {
        Optional<Policy> policyOptional = policyService.findByMatricula(matricula);
        return policyOptional
                .map(policy -> new ResponseEntity<>(policyDtoMapper.fromDomainToDto(policy), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<PolicyDto> getPolicyByDni(String dni){
        Optional<Policy> aux = policyService.findByDni(dni) ;
        return aux.map(policy -> new ResponseEntity<>(policyDtoMapper.fromDomainToDto(policy), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }







}
