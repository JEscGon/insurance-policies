package com.dev.insurance_policies.application.service;
import com.dev.insurance_policies.application.domain.Policy;
import com.dev.insurance_policies.application.repository.PolicyRepository;
import com.dev.insurance_policies.application.repository.UserRepository;
import com.dev.insurance_policies.application.repository.UserThirdRepository;
import com.dev.insurance_policies.application.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final UserThirdRepository userThirdRepository;

    public void save(Policy policy) {
        if (userRepository.findById(policy.getUserId()) == null) {
            throw new RuntimeException("User not found");
        }
        if (vehicleRepository.findById(policy.getVehicleId()) == null) {
            throw new RuntimeException("Vehicle not found");
        }
        if (userThirdRepository.findThirdUserById(policy.getBeneficiaryId()) == null) {
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
        var auxVehicle = vehicleRepository.findByMatricula(matricula);
        if (auxVehicle == null || auxVehicle.getId() == null) {
            throw new RuntimeException("Vehicle not found");
        }
        return policyRepository.findByUserId(Long.valueOf(auxVehicle.getUserId()));
    }

    public Optional<Policy> findByDni(String dni) {
        var auxUser = userRepository.getUserByDni(dni);
        return policyRepository.findByUserId(auxUser.getId());
    }

    public Optional<Policy> findByUserId(Integer userId) {
        return policyRepository.findByUserId(Long.valueOf(userId));
    }
}
