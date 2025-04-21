package com.dev.insurance_policies;

import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = InsurancePoliciesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PolicyApiIntegracionTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PolicyJpaRepository policyJpaRepository;

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
    @Test //TODO: ver como comprobar la respuesta ya que llama a otro microservicio
    public void findPolicyByNonExistentDniTest() throws Exception {
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

    @Test //TODO : ver como ver BD H2
    public void findPolicyByMatriculaTest() throws Exception {
        mockMvc.perform(get("/policies/matricula/1234ABC"))
                .andExpect(status().isOk());
    }
    @Test
    public void findPolicyByNonExistentMatriculaTest() throws Exception {
        mockMvc.perform(get("/policies/matricula/9999XYZ"))
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

    @Test //TODO : fix code 200 to 201
    public void savePolicyTest() throws Exception {
        String newPolicy = """
                {
                    "userId": 1,
                    "vehicleId": 1,
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "premiumAmount": 500,
                    "iban": "ES7620770024003102575766",
                    "active": true
                }
                """;
        mockMvc.perform(get("/policies")
                        .contentType("application/json")
                        .content(newPolicy))
                .andExpect(status().isCreated());
    }
    @Test //TODO :
    public void savePolicyWithInvalidDataTest() throws Exception {
        String newPolicy = """
                {
                    "userId": 1,
                    "vehicleId": 1,
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "premiumAmount": 500,
                    "iban": "invalid_iban",
                    "active": true
                }
                """;
        mockMvc.perform(get("/policies")
                        .contentType("application/json")
                        .content(newPolicy))
                .andExpect(status().isBadRequest());
    }
    @Test //TODO : duplicated key
    public void saveWithDuplicateKeyTest() throws Exception {
        String newPolicy = """
                {
                    "userId": 1,
                    "vehicleId": 1,
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "premiumAmount": 500,
                    "iban": "ES7620770024003102575766",
                    "active": true
                }
                """;
        mockMvc.perform(get("/policies")
                        .contentType("application/json")
                        .content(newPolicy))
                .andExpect(status().isConflict());
    }

    @Test
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
        mockMvc.perform(get("/policies/1")
                        .contentType("application/json")
                        .content(updatedPolicy))
                .andExpect(status().isOk());
    }

}
