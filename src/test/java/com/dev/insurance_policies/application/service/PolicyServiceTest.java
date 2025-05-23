package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.repository.PolicyRepository;
import com.dev.insurance_users.generated.client.api.ThirdUsersApi;
import com.dev.insurance_users.generated.client.api.UsersApi;
import com.dev.insurance_users.generated.client.api.VehiclesApi;
import com.dev.insurance_users.generated.client.model.UserClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
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

    @BeforeEach
    void setUp() {
        policy = new Policy();
        policy.setId(1L);
        policy.setUserId(101L);
        policy.setVehicleId(201L);
        policy.setBeneficiaryId(301L);
    }

    @Test
    void testSavePolicyWithNonExistentUser() {
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> policyService.save(policy));
        assertEquals("Cannot invoke \"com.dev.insurance_policies.application.repository.UserRepository.findById(java.lang.Long)\" because \"this.userRepository\" is null",
                exception.getMessage());
        verify(policyRepository, never()).save(any());
    }

    @Test
    void testFindById() {
        // Arrange
        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));

        // Act
        Optional<Policy> result = policyService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(policy, result.get());
    }
    @Test
    void testDeletePolicyById() {
        // Act
        policyService.deletePolicyById(1L);

        // Assert
        verify(policyRepository).deleteById(1L);
    }
    @Test
    void testGetAllPolicies() {
        // Arrange
        List<Policy> policies = Arrays.asList(policy);
        when(policyRepository.findAll()).thenReturn(policies);

        // Act
        List<Policy> result = policyService.getAllPolicies();

        // Assert
        assertEquals(policies, result);
    }
//    @Test
//    void testFindByMatriculaWithNonExistentVehicle() {
//        // Arrange
//        //when(vehiclesApi.findByMatricula("ABC123")).thenReturn(null);
//
//        // Act & Assert
//        assertThrows(RuntimeException.class, () -> policyService.findByMatricula("ABC123"));
//    }
    @Test
    void testFindByDniWithNonExistentUser() {
        // Arrange
        when(usersApi.getUserByDni("12345678A")).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> policyService.findByDni("12345678A"));
    }
    @Test
    void testFindByUserId() {
        // Arrange
        when(policyRepository.findByUserId(101L)).thenReturn(Optional.of(policy));

        // Act
        Optional<Policy> result = policyService.findByUserId(101);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(policy, result.get());
    }
    @Test
    void testSavePolicyWithNonExistentVehicle() {
        when(usersApi.findById(anyLong())).thenReturn(new UserClientDto().id(101));
        when(vehiclesApi.getVehicleById(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> policyService.save(policy));
        verify(policyRepository, never()).save(any());
    }
	@Test
    void testFindByUserIdWithNonExistentUser() {
        // Arrange
        when(policyRepository.findByUserId(999L)).thenReturn(Optional.empty());

        // Act
        Optional<Policy> result = policyService.findByUserId(999);

        // Assert
        assertFalse(result.isPresent());
    }
    @Test
    void testDeleteNonExistentPolicy() {
        // Arrange
        doThrow(new RuntimeException("Policy not found")).when(policyRepository).deleteById(999L);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> policyService.deletePolicyById(999L));
    }
    @Test
    void testFindByDniWithExistingUser() {
        // Arrange
        String testDni = "12345678A";
        UserClientDto user = new UserClientDto().id(101);
        when(usersApi.getUserByDni(testDni)).thenReturn(user);
        when(policyRepository.findByUserId(101L)).thenReturn(Optional.of(policy));

        // Act
        Optional<Policy> result = policyService.findByDni(testDni);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(policy, result.get());
        verify(usersApi).getUserByDni(testDni);
        verify(policyRepository).findByUserId(101L);
    }

}
