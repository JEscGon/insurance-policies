package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.exception.ResourceNotFoundException;
import com.dev.insurance_policies.application.repository.PolicyRepository;
import com.dev.insurance_users.generated.client.api.ThirdUsersApi;
import com.dev.insurance_users.generated.client.api.UsersApi;
import com.dev.insurance_users.generated.client.api.VehiclesApi;
import com.dev.insurance_users.generated.client.model.ThirdPartyUserClientDto;
import com.dev.insurance_users.generated.client.model.UserClientDto;
import com.dev.insurance_users.generated.client.model.VehicleClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PolicyServiceTest {

    @Mock
    private PolicyRepository policyRepository;
    @Mock
    private UsersApi usersApi;
    @Mock
    private VehiclesApi vehiclesApi;
    @Mock
    private ThirdUsersApi thirdUsersApi;
    @InjectMocks
    private PolicyService policyService;

    private Policy policy;
    private UserClientDto userClientDto;
    private VehicleClientDto vehicleClientDto;
    private ThirdPartyUserClientDto thirdPartyUserClientDto;

    @BeforeEach
    void setUp() {
        policy = Policy.builder()
                .id(1L)
                .userId(1L)
                .vehicleId(1L)
                .beneficiaryId(1L)
                .build();

        userClientDto = new UserClientDto()
                .id(1)
                .dni("12345678A");

        vehicleClientDto = new VehicleClientDto()
                .id(1)
                .matricula("1234ABC");

        thirdPartyUserClientDto = new ThirdPartyUserClientDto()
                .id(1)
                .dni("87654321Z");
    }

    @Test //TODO: fix test
    void save_ValidPolicy_SavesSuccessfully() {
        when(usersApi.findById(anyLong())).thenReturn(userClientDto);
        when(vehiclesApi.getVehicleById(anyLong())).thenReturn(vehicleClientDto);
        when(thirdUsersApi.findThirdUserById(anyLong())).thenReturn(thirdPartyUserClientDto);

        policyService.save(policy);

        verify(policyRepository).save(policy);
    }

    @Test //TODO: fix test
    void save_InvalidUser_ThrowsException() {
        when(usersApi.findById(anyLong())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> policyService.save(policy));
        verify(policyRepository, never()).save(any());
    }



    @Test
    void findById_ExistingPolicy_ReturnsPolicy() {
        when(policyRepository.findById(anyLong())).thenReturn(Optional.of(policy));

        Optional<Policy> result = policyService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(policy, result.get());
    }
    @Test
    void findById_NonExistingPolicy_ReturnsEmpty() {
        when(policyRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Policy> result = policyService.findById(1L);

        assertTrue(result.isEmpty());
    }
    @Test
    void getAllPolicies_ReturnsListOfPolicies() {
        List<Policy> policies = Collections.singletonList(policy);
        when(policyRepository.findAll()).thenReturn(policies);

        List<Policy> result = policyService.getAllPolicies();

        assertEquals(policies, result);
    }
    @Test
    void findByMatricula_ExistingMatricula_ReturnsPolicy() {
        when(policyRepository.findByMatricula(anyString())).thenReturn(Optional.of(policy));

        Optional<Policy> result = policyService.findByMatricula("1234ABC");

        assertTrue(result.isPresent());
        assertEquals(policy, result.get());
    }
    @Test
    void deletePolicyById_DeletesPolicy() {
        doNothing().when(policyRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> policyService.deletePolicyById(1L));
        verify(policyRepository).deleteById(1L);
    }
}



