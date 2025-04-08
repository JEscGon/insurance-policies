package com.dev.insurance_policies.application.service;
import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.repository.PolicyRepository;

import com.dev.insurance_users.generated.client.api.ThirdUsersApi;
import com.dev.insurance_users.generated.client.api.UsersApi;
import com.dev.insurance_users.generated.client.api.VehiclesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final UsersApi usersApi;
    private final VehiclesApi vehiclesApi;
    private final ThirdUsersApi thirdUsersApi;

    public void save(Policy policy) {
        if (usersApi.findById(policy.getUserId()) == null) {
            throw new RuntimeException("User not found");
        }
        if (vehiclesApi.getVehicleById(policy.getVehicleId()) == null) {
            throw new RuntimeException("Vehicle not found");
        }
        if (thirdUsersApi.findThirdUserById(policy.getBeneficiaryId()) == null) {
            throw new RuntimeException("Beneficiary not found");
        }
        policyRepository.save(policy);
    }

    public Optional<Policy> findById(Long id) {
        return policyRepository.findById(id);
    }

    public void deletePolicyById(Long id) {
        policyRepository.deleteById(id);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Optional<Policy> findByMatricula(String matricula) {
        var auxVehicle = vehiclesApi.findByMatricula(matricula);
        if (auxVehicle == null || auxVehicle.getId() == null) {
            throw new RuntimeException("Vehicle not found");
        }
        return policyRepository.findByUserId(Long.valueOf(auxVehicle.getUserId()));
    }

    public Optional<Policy> findByDni(String dni) {
        var auxUser = usersApi.getUserByDni(dni);
        if(auxUser == null || auxUser.getId() == null){
            throw new RuntimeException("User not found");
        }
        return policyRepository.findByUserId(Long.valueOf(auxUser.getId()));
    }

    public Optional<Policy> findByUserId(Integer userId) {
        return policyRepository.findByUserId(Long.valueOf(userId));
    }
}
