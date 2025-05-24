package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.exception.ResourceNotFoundException;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartServiceTest {

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

    @BeforeEach
    void setUp() {

        policy = new Policy();
        policy.setId(Long.valueOf("1"));

        part = new Part();
        part.setId(1L);
        part.setPolicyId(1L);
        part.setThirdPartyIds(Arrays.asList(1L, 2L));
        part.setThirdPartyVehicleIds(Arrays.asList(1L, 2L));
        part.setStateId(1L);
        part.setPlaceEvent("Madrid");
        part.setDescription("Accidente en intersección");
        part.setAccidentDate(LocalDateTime.now());
        part.setDateOfRegistration(LocalDateTime.now());
        part.setDateOfLastUpdate(LocalDateTime.now());
    }


    @Test
    public void testFindById() {
        // Arrange
        when(partRepository.findById(1L)).thenReturn(Optional.of(part));

        // Act
        Optional<Part> result = partService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(part, result.get());
    }

    @Test
    public void testFindByIdNotFound() {
        // Arrange
        when(partRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Part> result = partService.findById(99L);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeletePartById() {
        // Act
        partService.deletePartById(1L);

        // Assert
        verify(partRepository).deleteById(1L);
    }

    @Test
    public void testGetAllParts() {
        // Arrange
        List<Part> parts = Arrays.asList(part);
        when(partRepository.findAll()).thenReturn(parts);

        // Act
        List<Part> result = partService.getAllParts();

        // Assert
        assertEquals(parts, result);
    }

    @Test
    public void testGetPartsByPolicyId() {
        // Arrange
        List<Part> parts = Arrays.asList(part);
        when(partRepository.findAllByPolicyId("1")).thenReturn(parts);

        // Act
        List<Part> result = partService.getPartsByPolicyId("1");

        // Assert
        assertEquals(parts, result);
    }
    @Test
    public void testSave() {

        // Act
        partService.save(part);

        // Assert
        verify(partRepository).save(part);
    }


}

