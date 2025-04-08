package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.PolicyType;
import com.dev.insurance_policies.application.repository.PolicyTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyTypeServiceTest {

    @Mock
    private PolicyTypeRepository policyTypeRepository;
    @InjectMocks
    private PolicyTypeService policyTypeService;

    private PolicyType policyType;

    @BeforeEach
    void setUp() {
        policyType = new PolicyType();
        policyType.setId(1L);
        policyType.setName("Seguro de Automóvil");
        policyType.setDescription("Cobertura para vehículos a motor");
    }

    @Test
    void testSavePolicyType() {
        // Act
        policyTypeService.save(policyType);

        // Assert
        verify(policyTypeRepository).save(policyType);
    }
    @Test
    void testFindByIdWhenExists() {
        // Arrange
        when(policyTypeRepository.findById(1L)).thenReturn(Optional.of(policyType));

        // Act
        Optional<PolicyType> result = policyTypeService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(policyType, result.get());
        verify(policyTypeRepository).findById(1L);
    }
    @Test
    void testFindByIdWhenDoesNotExist() {
        // Arrange
        when(policyTypeRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        Optional<PolicyType> result = policyTypeService.findById(2L);

        // Assert
        assertFalse(result.isPresent());
        verify(policyTypeRepository).findById(2L);
    }
    @Test
    void testDeletePolicyTypeById() {
        // Act
        policyTypeService.deletePolicyTypeById(1L);

        // Assert
        verify(policyTypeRepository).deleteById(1L);
    }

}