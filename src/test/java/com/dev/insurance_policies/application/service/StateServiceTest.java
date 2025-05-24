package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.State;
import com.dev.insurance_policies.application.repository.StateRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StateServiceTest {

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private StateService stateService;

    private State state;

    @BeforeEach
    void setUp() {
        state = new State();
        state.setId(1L);
        state.setName("En proceso");
        state.setDescription("Estado para solicitudes en procesamiento");
    }

    @Test
    void testFindByIdWhenExists() {
        // Arrange
        when(stateRepository.findById(1L)).thenReturn(Optional.of(state));

        // Act
        Optional<State> result = stateService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(state, result.get());
        verify(stateRepository).findById(1L);
    }

    @Test
    void testFindByIdWhenDoesNotExist() {
        // Arrange
        when(stateRepository.findById(2L)).thenReturn(Optional.empty());

        // Act
        Optional<State> result = stateService.findById(2L);

        // Assert
        assertFalse(result.isPresent());
        verify(stateRepository).findById(2L);
    }

    @Test
    void testDeleteStateById() {
        // Act
        stateService.deleteStateById(1L);

        // Assert
        verify(stateRepository).deleteById(1L);
    }

    @Test
    void testGetAllStates() {
        // Arrange
        List<State> stateList = Arrays.asList(
            state,
            new State(2L, "Completado", "Estado para solicitudes procesadas")
        );
        when(stateRepository.findAll()).thenReturn(stateList);

        // Act
        List<State> result = stateService.getAllStates();

        // Assert
        assertEquals(2, result.size());
        assertEquals(stateList, result);
        verify(stateRepository).findAll();
    }
}