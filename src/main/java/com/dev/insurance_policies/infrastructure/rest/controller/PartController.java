package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.service.PartService;
import com.dev.insurance_policies.generated.controller.api.PartsApi;
import com.dev.insurance_policies.generated.controller.model.PartDto;
import com.dev.insurance_policies.infrastructure.rest.controller.mapper.PartDtoMapper;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PartController implements PartsApi {

    private final PartService partService;
    private final PartDtoMapper partDtoMapper;

    @Override
    public ResponseEntity<Void> savePart(PartDto partDto){
        partService.save(partDtoMapper.fromDtoToDomain(partDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PartDto>> getAllParts(){
        List<PartDto> partDtos = partService.getAllParts().stream()
                .map(partDtoMapper::fromDomainToDto)
                .toList();
        return new ResponseEntity<>(partDtos, HttpStatus.OK);
    }


    @Override     //TODO : Implementar findById
    public ResponseEntity<PartDto> getPartById(Integer id){
        return partService.findById(Long.valueOf(id))
                .map(part -> new ResponseEntity<>(partDtoMapper.fromDomainToDto(part), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //TODO: Implementar deleteById
    public ResponseEntity<Void> deletePartById(Integer id){
        partService.deletePartById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override //TODO: findAllbyPolicyId
    public ResponseEntity<List<PartDto>> getPartsByPolicyId(Integer policyId) {
        List<PartDto> partDtos = partService.getPartsByPolicyId(String.valueOf(policyId)).stream()
                .map(partDtoMapper::fromDomainToDto)
                .toList();
        return new ResponseEntity<>(partDtos, HttpStatus.OK);
    }
}
