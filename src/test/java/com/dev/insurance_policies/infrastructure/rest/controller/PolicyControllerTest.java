package com.dev.insurance_policies.infrastructure.rest.controller;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.service.PolicyService;
import com.dev.insurance_policies.generated.controller.model.PolicyDto;
import com.dev.insurance_policies.infrastructure.rest.mapper.PolicyDtoMapper;
import com.dev.insurance_users.generated.client.api.UsersApi;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PolicyControllerTest {

    @Mock
    private PolicyService policyService;
    @Mock
    private PolicyDtoMapper policyDtoMapper;
    @Mock
    private UsersApi usersApi;
    @InjectMocks
    private PolicyController policyController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(policyController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private Policy createPolicy() {
        Policy policy = new Policy();
        policy.setId(1L);
        policy.setUserId(1L);
        policy.setVehicleId(1L);
        policy.setBeneficiaryId(1L);
        policy.setPolicyTypeId(1L);
        policy.setIban("IBAN123456789");
        policy.setStartDate(LocalDate.now());
        policy.setEndDate(LocalDate.now().plusYears(1));
        policy.setRegistrationDate(LocalDate.now());
        policy.setLastUpdateDate(LocalDate.now());
        policy.setActive(true);
        policy.setPremiumAmount(100L);
        policy.setParts(List.of()); // Lista vacía de partes
        return policy;
    }
    private PolicyDto createPolicyDto() {
        PolicyDto policyDto = new PolicyDto(1, 1, 1, "IBAN123456789", LocalDate.now(), LocalDate.now().plusYears(1), 1, 100.0, LocalDate.now(), LocalDate.now());
        return policyDto;
    }

    @Test
    void getAllPolicies_deberiaRetornarListaDePolizas() throws Exception {
        // GIVEN
        Policy policy = createPolicy();
        PolicyDto policyDto = createPolicyDto();
        when(policyService.getAllPolicies()).thenReturn(List.of(policy));
        when(policyDtoMapper.fromDomainToDto(policy)).thenReturn(policyDto);

        // WHEN/THEN
        mockMvc.perform(get("/policies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(policyDto.getId()));

        verify(policyService).getAllPolicies();
        verify(policyDtoMapper).fromDomainToDto(policy);
    }
    @Test
    void getPolicyById_deberiaDevolverNotFound_cuandoPolizaNoExiste() throws Exception {
        // GIVEN
        when(policyService.findById(99L)).thenReturn(Optional.empty());

        // WHEN/THEN
        mockMvc.perform(get("/policies/99"))
                .andExpect(status().isNotFound());

        verify(policyService).findById(99L);
    }
    @Test
    void getPolicyById_deberiaDevolverBadRequest_cuandoIdNoValido() throws Exception {
        // WHEN/THEN
        mockMvc.perform(get("/policies/invalid"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void savePolicy_deberiaCrearPoliza() throws Exception {
        // GIVEN
        Policy policy = createPolicy();
        PolicyDto policyDto = createPolicyDto();

        when(policyDtoMapper.fromDtoToDomain(any(PolicyDto.class))).thenReturn(policy);
        doNothing().when(policyService).save(policy);

        // WHEN/THEN
        mockMvc.perform(post("/policies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(policyDto)))
                .andExpect(status().isCreated());

        verify(policyDtoMapper).fromDtoToDomain(any(PolicyDto.class));
        verify(policyService).save(policy);
    }
    @Test
    void updatePolicy_deberiaActualizarPoliza() throws Exception {
        // GIVEN
        Policy policy = createPolicy();
        PolicyDto policyDto = createPolicyDto();

        when(policyDtoMapper.fromDtoToDomain(any(PolicyDto.class))).thenReturn(policy);
        doNothing().when(policyService).save(policy);

        // WHEN/THEN
        mockMvc.perform(put("/policies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(policyDto)))
                .andExpect(status().isOk());

        verify(policyDtoMapper).fromDtoToDomain(any(PolicyDto.class));
        verify(policyService).save(policy);
    }
    @Test
    void updatePolicy_deberiaDevolverBadRequest_cuandoIdNoValido() throws Exception {
        // GIVEN
        PolicyDto policyDto = createPolicyDto();

        // WHEN/THEN
        mockMvc.perform(put("/policies/invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(policyDto)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void deletePolicyById_deberiaEliminarPoliza() throws Exception {
        // GIVEN
        doNothing().when(policyService).deletePolicyById(1L);

        // WHEN/THEN
        mockMvc.perform(delete("/policies/1"))
                .andExpect(status().isNoContent());

        verify(policyService).deletePolicyById(1L);
    }
    @Test
    void getPolicyByDni_deberiaDevolverNotFound_cuandoPolizaNoExiste() throws Exception {
        // GIVEN
        String dni = "99999999Z";
        when(policyService.findByDni(dni)).thenReturn(Optional.empty());

        // WHEN/THEN
        mockMvc.perform(get("/policies/dni/{dni}", dni))
                .andExpect(status().isNotFound());

        verify(policyService).findByDni(dni);
    }
    @Test
    void getPolicyByUserId_deberiaRetornarPoliza() throws Exception {
        // GIVEN
        Integer userId = 1;
        Policy policy = createPolicy();
        PolicyDto policyDto = createPolicyDto();

        when(policyService.findByUserId(userId)).thenReturn(Optional.of(policy));
        when(policyDtoMapper.fromDomainToDto(policy)).thenReturn(policyDto);

        // WHEN/THEN
        mockMvc.perform(get("/policies/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId));

        verify(policyService).findByUserId(userId);
        verify(policyDtoMapper).fromDomainToDto(policy);
    }
    @Test
    void getPolicyByUserId_deberiaDevolverNotFound_cuandoPolizaNoExiste() throws Exception {
        // GIVEN
        Integer userId = 99;
        when(policyService.findByUserId(userId)).thenReturn(Optional.empty());

        // WHEN/THEN
        mockMvc.perform(get("/policies/user/{userId}", userId))
                .andExpect(status().isNotFound());

        verify(policyService).findByUserId(userId);
    }

}