package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PolicyEntity;
import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyTypeJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.PolicyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PolicyRepositoryImplTest {

    @Mock
    private PolicyMapper policyMapper;
    @Mock
    private PolicyJpaRepository policyJpaRepository;
    @Mock
    private PolicyTypeJpaRepository policyTypeJpaRepository;

    @InjectMocks
    private PolicyRepositoryImpl policyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Policy policy = mock(Policy.class);
        PolicyEntity policyEntity = mock(PolicyEntity.class);

        when(policyMapper.fromDomainToEntity(policy)).thenReturn(policyEntity);
        when(policy.getId()).thenReturn(null);

        policyRepository.save(policy);

        verify(policyEntity).setRegistrationDate(any());
        verify(policyJpaRepository).save(policyEntity);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        PolicyEntity policyEntity = mock(PolicyEntity.class);
        Policy policy = mock(Policy.class);

        when(policyJpaRepository.findById(id)).thenReturn(Optional.of(policyEntity));
        when(policyMapper.fromEntityToDomain(policyEntity)).thenReturn(policy);

        Optional<Policy> result = policyRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(policy, result.get());
        verify(policyJpaRepository).findById(id);
        verify(policyMapper).fromEntityToDomain(policyEntity);
    }

    @Test
    void testFindAll() {
        List<PolicyEntity> policyEntities = List.of(mock(PolicyEntity.class), mock(PolicyEntity.class));
        List<Policy> policies = List.of(mock(Policy.class), mock(Policy.class));

        when(policyJpaRepository.findAll()).thenReturn(policyEntities);
        when(policyMapper.fromEntityToDomain(any(PolicyEntity.class)))
            .thenReturn(policies.get(0), policies.get(1));

        List<Policy> result = policyRepository.findAll();

        assertEquals(2, result.size());
        verify(policyJpaRepository).findAll();
        verify(policyMapper, times(2)).fromEntityToDomain(any(PolicyEntity.class));
    }

    @Test
    void testFindByUserId() {
        Long userId = 1L;
        PolicyEntity policyEntity = mock(PolicyEntity.class);
        Policy policy = mock(Policy.class);

        when(policyJpaRepository.findByUserId(userId)).thenReturn(Optional.of(policyEntity));
        when(policyMapper.fromEntityToDomain(policyEntity)).thenReturn(policy);

        Optional<Policy> result = policyRepository.findByUserId(userId);

        assertTrue(result.isPresent());
        assertEquals(policy, result.get());
        verify(policyJpaRepository).findByUserId(userId);
        verify(policyMapper).fromEntityToDomain(policyEntity);
    }

    @Test
    void testDeleteById() {
        policyRepository.deleteById(1L);

        verify(policyJpaRepository).deleteById(1L);
    }

    @Test
    void testSaveWithNonExistentId() {
        Policy policy = mock(Policy.class);
        PolicyEntity policyEntity = mock(PolicyEntity.class);

        when(policyMapper.fromDomainToEntity(policy)).thenReturn(policyEntity);
        when(policy.getId()).thenReturn(1L);
        when(policyJpaRepository.findById(1L)).thenReturn(Optional.empty());

        policyRepository.save(policy);

        verify(policyEntity, never()).setRegistrationDate(any());
        verify(policyJpaRepository).save(policyEntity);
    }

    @Test
    void testSaveWithNonExistentPolicyType() {
        Policy policy = mock(Policy.class);
        PolicyEntity policyEntity = mock(PolicyEntity.class);

        when(policyMapper.fromDomainToEntity(policy)).thenReturn(policyEntity);
        when(policy.getId()).thenReturn(1L);
        when(policyJpaRepository.findById(1L)).thenReturn(Optional.of(policyEntity));
        when(policy.getPolicyTypeId()).thenReturn(2L);
        when(policyTypeJpaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> policyRepository.save(policy));
    }

    @Test
    void testFindByIdWhenNotExists() {
        Long id = 1L;

        when(policyJpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Policy> result = policyRepository.findById(id);

        assertFalse(result.isPresent());
        verify(policyJpaRepository).findById(id);
        verify(policyMapper, never()).fromEntityToDomain(any());
    }


    @Test
    void testFindByUserIdWhenNotExists() {
        Long userId = 1L;

        when(policyJpaRepository.findByUserId(userId)).thenReturn(Optional.empty());

        Optional<Policy> result = policyRepository.findByUserId(userId);

        assertFalse(result.isPresent());
        verify(policyJpaRepository).findByUserId(userId);
        verify(policyMapper, never()).fromEntityToDomain(any());
    }

    @Test
    void testDeleteByIdWhenNotExists() {
        doThrow(new IllegalArgumentException("ID not found")).when(policyJpaRepository).deleteById(1L);

        assertThrows(IllegalArgumentException.class, () -> policyRepository.deleteById(1L));
        verify(policyJpaRepository).deleteById(1L);
    }

}

