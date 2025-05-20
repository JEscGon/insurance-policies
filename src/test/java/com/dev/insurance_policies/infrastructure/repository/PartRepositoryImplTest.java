package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PolicyEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.PartJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.PartMapper;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PartRepositoryImplTest {

    @Mock
    private PartJpaRepository partJpaRepository;

    @Mock
    private PartMapper partMapper;

    @InjectMocks
    private PartRepositoryImpl partRepository;

    private Part part;
    private PartEntity partEntity;

    @BeforeEach
    void setUp() {
        // Preparar objetos para tests
        part = new Part();
        part.setId(1L);
        part.setDescription("Descripción del parte");

        PolicyEntity policyEntity = new PolicyEntity();
        policyEntity.setId(101L); // Configurar el ID de la póliza

        partEntity = new PartEntity();
        partEntity.setId(1L);
        partEntity.setPolicy(policyEntity); // Asignar la póliza correctamente
        partEntity.setDescription("Descripción del parte");
        partEntity.setDateOfRegistration(LocalDateTime.now());
    }

    @Test
    void testUpdateExistingPart() {
        // Arrange
        when(partMapper.fromDomainToEntity(part)).thenReturn(partEntity);
        when(partJpaRepository.findById(part.getId())).thenReturn(Optional.of(partEntity));

        // Act
        partRepository.save(part);

        // Assert
        verify(partMapper).updatePartFromExisting(eq(partEntity), eq(partEntity));
        verify(partJpaRepository).save(partEntity);
        assertNotNull(partEntity.getDateOfLastUpdate());
    }

    @Test
    void testFindByIdWhenExists() {
        // Arrange
        when(partJpaRepository.findById(1L)).thenReturn(Optional.of(partEntity));
        when(partMapper.fromEntityToDomain(partEntity)).thenReturn(part);

        // Act
        Optional<Part> result = partRepository.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(part, result.get());
    }

    @Test
    void testFindByIdWhenNotExists() {
        // Arrange
        when(partJpaRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        Optional<Part> result = partRepository.findById(2L);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteById() {
        // Act
        partRepository.deleteById(1L);

        // Assert
        verify(partJpaRepository).deleteById(1L);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<PartEntity> entities = Arrays.asList(partEntity);
        when(partJpaRepository.findAll()).thenReturn(entities);
        when(partMapper.fromEntityToDomain(partEntity)).thenReturn(part);

        // Act
        List<Part> results = partRepository.findAll();

        // Assert
        assertEquals(1, results.size());
        assertEquals(part, results.get(0));
    }

    @Test
    void testFindAllByPolicyId() {
        // Arrange
        List<PartEntity> entities = Arrays.asList(partEntity);
        when(partJpaRepository.findAllByPolicyId(101L)).thenReturn(entities);
        when(partMapper.fromEntityToDomain(partEntity)).thenReturn(part);

        // Act
        List<Part> results = partRepository.findAllByPolicyId("101");

        // Assert
        assertEquals(1, results.size());
        assertEquals(part, results.get(0));
        verify(partJpaRepository).findAllByPolicyId(101L);
    }

    @Test
    void testSaveNewPart() {
        // Arrange
        part.setId(null); // Nuevo Part sin ID
        when(partMapper.fromDomainToEntity(part)).thenReturn(partEntity);

        // Act
        partRepository.save(part);

        // Assert
        assertNotNull(partEntity.getDateOfRegistration());
        verify(partJpaRepository).save(partEntity);
    }

    @Test
    void testSavePartWithNonExistentId() {
        // Arrange
        when(partMapper.fromDomainToEntity(part)).thenReturn(partEntity);
        when(partJpaRepository.findById(part.getId())).thenReturn(Optional.empty());

        // Act
        partRepository.save(part);

        // Assert
        verify(partJpaRepository).save(partEntity);
        assertNull(partEntity.getDateOfLastUpdate());
    }
}