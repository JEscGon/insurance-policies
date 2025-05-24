package com.dev.insurance_policies;

import com.dev.insurance_policies.infrastructure.repository.jpa.PolicyTypeJpaRepository;
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
public class PolicyTypeApiIntegracionTest {

     @Autowired
     private MockMvc mockMvc;
     @Autowired
     private PolicyTypeJpaRepository policyTypeJpaRepository;

//     @Test
//     public void findPolicyTypeByIdTest() throws Exception {
//         mockMvc.perform(get("/policy-types/1"))
//                 .andExpect(status().isNotFound());
//     }
//     @Test
//     public void findPolicyTypeByIdNotFoundTest() throws Exception {
//         mockMvc.perform(get("/policy-types/999"))
//                 .andExpect(status().isNotFound());
//     }

}
