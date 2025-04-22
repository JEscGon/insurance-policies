package com.dev.insurance_policies;


import com.dev.insurance_policies.infrastructure.repository.jpa.PartJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = InsurancePoliciesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PartApiIntegracionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PartJpaRepository partJpaRepository;

    @Test
    public void deletePartByIdTest() throws Exception {
        mockMvc.perform(delete("/parts/2"))
                .andExpect(status().isNoContent());
    }
    @Test
    public void deletePartByIdNotFoundTest() throws Exception {
        mockMvc.perform(delete("/parts/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllPartsTest() throws Exception {
        mockMvc.perform(get("/parts"))
                .andExpect(status().isOk());
    }

    @Test
    public void findPartByIdTest() throws Exception {
        mockMvc.perform(get("/parts/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void findPartByIdNotFoundTest() throws Exception {
        mockMvc.perform(get("/parts/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllPartsByPolicyIdTest() throws Exception {
        mockMvc.perform(get("/parts/policy/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void findAllPartsByPolicyIdNotFoundTest() throws Exception {
        mockMvc.perform(get("/parts/policy/999"))
                .andExpect(status().isNotFound());
    }

    @Test // TODO: fix
    public void savePartTest() throws Exception {
        String newPart = """
            {
                "policyId": 1,
                "stateId": 1,
                "thirdPartyId": 1,
                "thirdPartyVehicleId": 1,
                "placeEvent": "Lugar 1",
                "description": "Descripción 1",
                "accidentDate": "2023-01-01 10:00:00"
            }
                """;
        mockMvc.perform(post("/parts")
                        .contentType("application/json")
                        .content(newPart))
                .andExpect(status().isCreated());
    }
    @Test // TODO: fix
    public void savePartWithInvalidDataTest() throws Exception {
        String newPart = """
                {
                    "policyId": 1,
                    "stateId": 2,
                    "thirdPartyId": 3,
                    "thirdPartyVehicleId": 4,
                    "placeEvent": "Lugar del accidente",
                    "description": "Parte de prueba",
                    "accidentDate": "2023-10-01T10:00:00"
                }
                """;
        mockMvc.perform(post("/parts")
                        .contentType("application/json")
                        .content(newPart))
                .andExpect(status().isBadRequest());
    }
    @Test // TODO: fix
    public void savePartWithDuplicateKeyTest() throws Exception {
        String newPart = """
                {
                    "policyId": 1,
                    "stateId": 2,
                    "thirdPartyId": 3,
                    "thirdPartyVehicleId": 4,
                    "placeEvent": "Lugar del accidente",
                    "description": "Parte de prueba",
                    "accidentDate": "2023-10-01T10:00:00"
                }
                """;
        mockMvc.perform(post("/parts")
                        .contentType("application/json")
                        .content(newPart))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePartTest() throws Exception {
        String updatedPart = """
                {
                    "stateId": 2,
                    "thirdPartyId": 3,
                    "thirdPartyVehicleId": 4,
                    "placeEvent": "Lugar del accidente actualizado",
                    "description": "Parte de prueba actualizado",
                    "accidentDate": "2023-10-01T10:00:00"
                }
                """;
        mockMvc.perform(put("/parts/1")
                        .contentType("application/json")
                        .content(updatedPart))
                .andExpect(status().isOk());
    }

}


