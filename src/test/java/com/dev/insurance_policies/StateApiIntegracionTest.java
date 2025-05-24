//package com.dev.insurance_policies;
//
//
//import com.dev.insurance_policies.infrastructure.repository.jpa.StateJpaRepository;
//import com.dev.insurance_policies.infrastructure.repository.jpa.entity.StateEntity;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(classes = InsurancePoliciesApplication.class,
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//public class StateApiIntegracionTest {
//
//     @Autowired
//     private MockMvc mockMvc;
//     @Autowired
//     private StateJpaRepository stateJpaRepository;
//
//    @BeforeEach
//    void setUp() {
//        testState = new StateEntity();
//        testState.setName("Estado de prueba");
//        testState.setDescription("Descripción de prueba");
//        testState = stateJpaRepository.save(testState);
//    }
//
//    @AfterEach
//    void tearDown() {
//        stateJpaRepository.deleteAll();
//    }
//
//    @Test
//    public void findAllStatesTest() throws Exception {
//        mockMvc.perform(get("/states"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].name").value(testState.getName()))
//                .andExpect(jsonPath("$[0].description").value(testState.getDescription()));
//    }
//
//    @Test
//    public void findStateByIdTest() throws Exception {
//        mockMvc.perform(get("/states/" + testState.getId()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name").value(testState.getName()))
//                .andExpect(jsonPath("$.description").value(testState.getDescription()));
//    }
//
//    @Test
//    public void findStateByIdNotFoundTest() throws Exception {
//        mockMvc.perform(get("/states/999"))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//}
