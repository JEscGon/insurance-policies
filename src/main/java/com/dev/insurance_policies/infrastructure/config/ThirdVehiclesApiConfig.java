package com.dev.insurance_policies.infrastructure.config;

import com.dev.insurance_users.generated.client.ApiClient;
import com.dev.insurance_users.generated.client.api.ThirdUsersApi;
import com.dev.insurance_users.generated.client.api.ThirdVehiclesApi;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ThirdVehiclesApiConfig {
    @Bean
    public ThirdVehiclesApi thirdVehiclesApi(RestTemplate restTemplate) {;
        ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath("http://localhost:8080");
        return new ThirdVehiclesApi(apiClient);
    }
}