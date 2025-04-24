package com.dev.insurance_policies;


import com.dev.insurance_policies.infrastructure.repository.jpa.StateJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = InsurancePoliciesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StateApiIntegracionTest {

     @Autowired
     private MockMvc mockMvc;
     @Autowired
     private StateJpaRepository stateJpaRepository;

//     @Test
//     public void findAllStatesTest() throws Exception {
//         mockMvc.perform(get("/states"))
//                 .andExpect(status().isOk());
//     }
//
//     @Test
//     public void findStateByIdTest() throws Exception {
//         mockMvc.perform(get("/states/1"))
//                 .andExpect(status().isOk());
//     }
//     @Test
//     public void findStateByIdNotFoundTest() throws Exception {
//         mockMvc.perform(get("/states/999"))
//                 .andExpect(status().isNotFound());
//     }

}
