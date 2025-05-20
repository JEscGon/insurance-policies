package com.dev.insurance_policies;

import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyJpaRepository;
import com.dev.insurance_users.generated.client.ApiClient;
import com.dev.insurance_users.generated.client.api.UsersApi;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = InsurancePoliciesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PolicyApiIntegracionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PolicyJpaRepository policyJpaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @MockitoBean
    private UsersApi apiClient;

    @Test
    public void deletePolicyByIdTest() throws Exception {
        mockMvc.perform(get("/policies/2"))
                .andExpect(status().isOk());
    }
    @Test
    public void deletePolicyByNonExistentIdTest() throws Exception {
        mockMvc.perform(get("/policies/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAllPoliciesTest() throws Exception {
        mockMvc.perform(get("/policies"))
                .andExpect(status().isOk());
    }

    @Test
    public void findPolicyBuDniTest() throws Exception {
        mockMvc.perform(get("/policies/dni/12345678A"))
                .andExpect(status().isOk());
    }
    @Test
    public void findPolicyByNonExistentDniTest() throws Exception {
         when(apiClient.getUserByDni("87654321B"))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/policies/dni/87654321B"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findPolicyByIdTest() throws Exception {
        mockMvc.perform(get("/policies/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void findPolicyByNonExistentIdTest() throws Exception {
        mockMvc.perform(get("/policies/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findPolicyByMatriculaTest() throws Exception {
        mockMvc.perform(get("/policies/vehicle/1234ABC"))
                .andExpect(status().isOk());
    }
    @Test //TODO: ver como comprobar la respuesta ya que llama a otro microservicio
    public void findPolicyByNonExistentMatriculaTest() throws Exception {
        mockMvc.perform(get("/policies/vehicle/9999XYZ"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findPolicyByUserIdTest() throws Exception {
        mockMvc.perform(get("/policies/user/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void findPolicyByNonExistentUserIdTest() throws Exception {
        mockMvc.perform(get("/policies/user/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void savePolicyTest() throws Exception {
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
    public void savePolicyWithInvalidDataTest() throws Exception {
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
    public void saveWithDuplicateKeyTest() throws Exception {
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
    public void updatePolicyTest() throws Exception {
        String updatedPolicy = """
                {
                    "userId": 1,
                    "vehicleId": 1,
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "premiumAmount": 600,
                    "iban": "ES7620770024003102575766",
                    "active": true
                }
                """;
        mockMvc.perform(put("/policies/1")
                        .contentType("application/json")
                        .content(updatedPolicy))
                .andExpect(status().isOk());
    }

}
