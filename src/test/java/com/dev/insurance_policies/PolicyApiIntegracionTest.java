package com.dev.insurance_policies;

import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyJpaRepository;
import com.dev.insurance_users.generated.client.api.UsersApi;
import com.dev.insurance_users.generated.client.model.UserClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = InsurancePoliciesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PolicyApiIntegracionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PolicyJpaRepository policyJpaRepository;
    @Autowired
    private RestTemplate restTemplate;
    @MockitoBean
    private UsersApi apiClient;

    @Test
    void deletePolicyByIdTest() throws Exception {
        mockMvc.perform(get("/policies/2"))
                .andExpect(status().isOk());
    }
    @Test
    void deletePolicyByNonExistentIdTest() throws Exception {
        mockMvc.perform(get("/policies/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAllPoliciesTest() throws Exception {
        mockMvc.perform(get("/policies"))
                .andExpect(status().isOk());
    }

    @Test
    void findPolicyBuDniTest() throws Exception {
        when(apiClient.getUserByDni("12345678A"))
                .thenReturn(new UserClientDto());
        mockMvc.perform(get("/policies/dni/12345678A"))
                .andExpect(status().isOk());
    }
    @Test
    void findPolicyByNonExistentDniTest() throws Exception {
         when(apiClient.getUserByDni("87654321B"))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/policies/dni/87654321B"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findPolicyByIdTest() throws Exception {
        mockMvc.perform(get("/policies/1"))
                .andExpect(status().isOk());
    }
    @Test
    void findPolicyByNonExistentIdTest() throws Exception {
        mockMvc.perform(get("/policies/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findPolicyByMatriculaTest() throws Exception {
        mockMvc.perform(get("/policies/vehicle/1234ABC"))
                .andExpect(status().isOk());
    }
    @Test
    void findPolicyByNonExistentMatriculaTest() throws Exception {
        mockMvc.perform(get("/policies/vehicle/9999XYZ"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findPolicyByUserIdTest() throws Exception {
        mockMvc.perform(get("/policies/user/1"))
                .andExpect(status().isOk());
    }
    @Test
    void findPolicyByNonExistentUserIdTest() throws Exception {
        mockMvc.perform(get("/policies/user/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void savePolicyTest() throws Exception {
        when(apiClient.findById(1L)).thenReturn(new UserClientDto()); // Simula que el usuario existe

        String newPolicy = """
                {
                    "userId": 1,
                    "vehicleId": 1,
                    "beneficiaryId": 1,
                    "policyTypeId": 1,
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "premiumAmount": 500,
                    "iban": "ES7620770024003102575766",
                    "active": true
                }
                """;
        mockMvc.perform(post("/policies")
                        .contentType("application/json")
                        .content(newPolicy))
                .andExpect(status().isCreated());
    }

    @Test
    void savePolicyWithInvalidDataTest() throws Exception {
        when(apiClient.findById(1L)).thenReturn(new UserClientDto()); // Simula que el usuario existe

        String newPolicy = """
                {
                    "userId": 1,
                    "vehicleId": 1,
                    "beneficiaryId": 1,
                    "iban": "ES7921000444610123456789",
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "policyTypeId": 1,
                    "active": true,
                    "premiumAmount": 1000
                }
                """;
        mockMvc.perform(post("/policies") // Cambiado de get a post
                        .contentType("application/json")
                        .content(newPolicy))
                .andExpect(status().isConflict());
    }

    @Test //TODO : duplicated key
    void saveWithDuplicateKeyTest() throws Exception {
        when(apiClient.findById(1L)).thenReturn(new UserClientDto()); // Simula que el usuario existe
        String newPolicy = """
                {
                    "userId": 1,
                    "vehicleId": 1,
                    "beneficiaryId": 1,
                    "policyTypeId": 1,
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "premiumAmount": 500,
                    "iban": "ES7620770024003102575766",
                    "active": true
                }
                """;
        mockMvc.perform(post("/policies")
                .contentType("application/json")
                .content(newPolicy))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/policies")
                .contentType("application/json")
                .content(newPolicy))
                .andExpect(status().isConflict());
    }

    @Test // TODO:
    void updatePolicyTest() throws Exception {
        when(apiClient.findById(1L)).thenReturn(new UserClientDto()); // Simula que el usuario existe

        String updatedPolicy = """
                {
                    "userId": 1,
                    "vehicleId": 1,
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "premiumAmount": 600,
                    "iban": "------------------------",
                    "active": true
                }
                """;
        mockMvc.perform(put("/policies/2")
                        .contentType("application/json")
                        .content(updatedPolicy))
                .andExpect(status().isOk());
    }

}
