package com.dev.insurance_policies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dev.insurance_policies")
public class InsurancePoliciesApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsurancePoliciesApplication.class, args);
	}
}
