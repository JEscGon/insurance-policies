package com.dev.insurance_policies.infrastructure.rest.controller;


import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.application.service.PartService;
import com.dev.insurance_policies.generated.controller.model.PartDto;
import com.dev.insurance_policies.infrastructure.rest.mapper.PartDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PartControllerTest {

    @Mock
    private PartService partService;
    @Mock
    private PartDtoMapper partDtoMapper;
    @InjectMocks
    private PartController partController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(partController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private Part createPart() {
        Part part = new Part();
        part.setId(1L);
        part.setPolicyId(1L);
        part.setStateId(1L);
//        part.setThirdPartyId(2L);
//        part.setThirdPartyVehicleId(2L);
        part.setPlaceEvent("Madrid, Calle Principal");
        part.setDescription("Colisión lateral");
        part.setAccidentDate(LocalDateTime.now());
        part.setDateOfRegistration(LocalDateTime.now());
        part.setDateOfLastUpdate(LocalDateTime.now());
        return part;
    }
    private PartDto createPartDto() {
        PartDto partDto = new PartDto();
        partDto.setId(1);
        partDto.setPolicyId(1);
        partDto.setStateId(1);
//        partDto.setThirdPartyId(2);
//        partDto.setThirdPartyVehicleId(2);
        partDto.setPlaceEvent("Madrid, Calle Principal");
        partDto.setDescription("Colisión lateral");
        partDto.setAccidentDate(OffsetDateTime.now());
        partDto.setDateOfRegistration(OffsetDateTime.now());
        partDto.setDateOfLastUpdate(OffsetDateTime.now());
        return partDto;
    }

    @Test
    void getAllParts_deberiaRetornarListaDePartes() throws Exception {
        // GIVEN
        Part part = createPart();
        PartDto partDto = createPartDto();
        when(partService.getAllParts()).thenReturn(List.of(part));
        when(partDtoMapper.fromDomainToDto(part)).thenReturn(partDto);

        // WHEN/THEN
        mockMvc.perform(get("/parts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(partDto.getId()));

        verify(partService).getAllParts();
        verify(partDtoMapper).fromDomainToDto(part);
    }
    @Test
    void savePart_deberiaCrearParte() throws Exception {
        // GIVEN
        Part part = createPart();
        PartDto partDto = createPartDto();

        when(partDtoMapper.fromDtoToDomain(any(PartDto.class))).thenReturn(part);
        doNothing().when(partService).save(part);

        // WHEN/THEN
        mockMvc.perform(post("/parts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(partDto)))
                .andExpect(status().isCreated());

        verify(partDtoMapper).fromDtoToDomain(any(PartDto.class));
        verify(partService).save(part);
    }
    @Test
    void getPartById_deberiaRetornarParte() throws Exception {
        // GIVEN
        Part part = createPart();
        PartDto partDto = createPartDto();

        when(partService.findById(1L)).thenReturn(Optional.of(part));
        when(partDtoMapper.fromDomainToDto(part)).thenReturn(partDto);

        // WHEN/THEN
        mockMvc.perform(get("/parts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Colisión lateral"));

        verify(partService).findById(1L);
        verify(partDtoMapper).fromDomainToDto(part);
    }
    @Test
    void getPartById_deberiaDevolverNotFound_cuandoParteNoExiste() throws Exception {
        // GIVEN
        when(partService.findById(99L)).thenReturn(Optional.empty());

        // WHEN/THEN
        mockMvc.perform(get("/parts/99"))
                .andExpect(status().isNotFound());

        verify(partService).findById(99L);
    }
    @Test //TODO:
    void deletePartById_deberiaEliminarParte() throws Exception {
        // GIVEN
        when(partService.existsById(1L)).thenReturn(true);
        doNothing().when(partService).deletePartById(1L);

        // WHEN/THEN
        mockMvc.perform(delete("/parts/1"))
                .andExpect(status().isNoContent());

        verify(partService).existsById(1L);
        verify(partService).deletePartById(1L);
    }
    @Test
    void getPartsByPolicyId_deberiaRetornarListaDePartes() throws Exception {
        // GIVEN
        Part part = createPart();
        PartDto partDto = createPartDto();
        Integer policyId = 1;

        when(partService.getPartsByPolicyId(String.valueOf(policyId))).thenReturn(List.of(part));
        when(partDtoMapper.fromDomainToDto(part)).thenReturn(partDto);

        // WHEN/THEN
        mockMvc.perform(get("/parts/policy/{policyId}", policyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].policyId").value(policyId));

        verify(partService).getPartsByPolicyId(String.valueOf(policyId));
        verify(partDtoMapper).fromDomainToDto(part);
    }

    @Test //TODO:
    void getPartsByPolicyId_deberiaRetornarListaVacia_cuandoNoHayPartesParaLaPoliza() throws Exception {
        // GIVEN
        Long policyId = 99L;
        String policyIdStr = String.valueOf(policyId);
        when(partService.getPartsByPolicyId(policyIdStr)).thenReturn(List.of());

        // WHEN/THEN
        mockMvc.perform(get("/parts/policy/{policyId}", policyId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(partService).getPartsByPolicyId(policyIdStr);
        verifyNoMoreInteractions(partService);
    }
}