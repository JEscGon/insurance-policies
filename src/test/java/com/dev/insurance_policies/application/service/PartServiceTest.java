package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.repository.PartRepository;
import com.dev.insurance_policies.application.repository.PolicyRepository;
import com.dev.insurance_users.generated.client.api.ThirdUsersApi;
import com.dev.insurance_users.generated.client.api.ThirdVehiclesApi;
import com.dev.insurance_users.generated.client.api.UsersApi;
import com.dev.insurance_users.generated.client.api.VehiclesApi;

import com.dev.insurance_users.generated.client.model.ThirdPartyUserClientDto;
import com.dev.insurance_users.generated.client.model.ThirdPartyVehicleClientDto;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PartServiceTest {

    @Mock
    private PartRepository partRepository;

    @Mock
    private PolicyRepository policyRepository;

    @Mock
    private ThirdVehiclesApi thirdVehiclesApi;

    @Mock
    private ThirdUsersApi thirdUsersApi;

    @Mock
    private UsersApi usersApi;

    @Mock
    private VehiclesApi vehiclesApi;

    @InjectMocks
    private PartService partService;

    private Part part;
    private Policy policy;

//    @BeforeEach
//    public void setUp() {
//        policy = new Policy();
//        policy.setId(1L);
//
//        part = new Part();
//        part.setId(1L);
//        part.setPolicyId(1L);
//        part.setThirdPartyVehicleId(2L);
//        part.setThirdPartyId(3L);
//    }

//    @Test
//    public void testSavePartWithNonExistentPolicy() {
//        // Arrange
//        when(policyRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(RuntimeException.class, () -> partService.save(part));
//        verify(partRepository, never()).save(any());
//    }
//
//    @Test
//    public void testSavePartWithNonExistentThirdVehicle() {
//        // Arrange
//        when(policyRepository.findById(anyLong())).thenReturn(Optional.of(policy));
//        when(thirdVehiclesApi.getThirdVehicleById(anyLong())).thenReturn(null);
//
//        // Act & Assert
//        assertThrows(RuntimeException.class, () -> partService.save(part));
//        verify(partRepository, never()).save(any());
//    }
//
//    @Test
//    public void testFindById() {
//        // Arrange
//        when(partRepository.findById(1L)).thenReturn(Optional.of(part));
//
//        // Act
//        Optional<Part> result = partService.findById(1L);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(part, result.get());
//    }
//
//    @Test
//    public void testDeletePartById() {
//        // Act
//        partService.deletePartById(1L);
//
//        // Assert
//        verify(partRepository).deleteById(1L);
//    }
//
//    @Test
//    public void testGetAllParts() {
//        // Arrange
//        List<Part> parts = Arrays.asList(part);
//        when(partRepository.findAll()).thenReturn(parts);
//
//        // Act
//        List<Part> result = partService.getAllParts();
//
//        // Assert
//        assertEquals(parts, result);
//    }
//
//    @Test
//    public void testGetPartsByPolicyId() {
//        // Arrange
//        List<Part> parts = Arrays.asList(part);
//        when(partRepository.findAllByPolicyId("1")).thenReturn(parts);
//
//        // Act
//        List<Part> result = partService.getPartsByPolicyId("1");
//
//        // Assert
//        assertEquals(parts, result);
//    }
//
//    @Test
//    public void testSavePartWithNonExistentThirdUser() {
//    // Arrange
//    when(policyRepository.findById(anyLong())).thenReturn(Optional.of(policy));
//    when(thirdVehiclesApi.getThirdVehicleById(anyLong())).thenReturn(mock(ThirdPartyVehicleClientDto.class));
//    when(thirdUsersApi.findThirdUserById(anyLong())).thenReturn(null);
//
//    // Act & Assert
//    assertThrows(RuntimeException.class, () -> partService.save(part));
//    verify(partRepository, never()).save(any());
//    }
//
//    @Test
//    public void testSavePartSuccessfully() {
//    // Arrange
//    when(policyRepository.findById(anyLong())).thenReturn(Optional.of(policy));
//    when(thirdVehiclesApi.getThirdVehicleById(anyLong())).thenReturn(mock(ThirdPartyVehicleClientDto.class)); // O cualquier objeto no nulo
//    when(thirdUsersApi.findThirdUserById(anyLong())).thenReturn(mock(ThirdPartyUserClientDto.class)); // O cualquier objeto no nulo
//    // Act
//    partService.save(part);
//    // Assert
//    verify(partRepository).save(part);
//    }

}