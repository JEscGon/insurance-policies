package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.application.repository.PartRepository;
import com.dev.insurance_policies.application.repository.PolicyRepository;
import com.dev.insurance_users.generated.client.api.ThirdUsersApi;
import com.dev.insurance_users.generated.client.api.ThirdVehiclesApi;
import com.dev.insurance_users.generated.client.api.UsersApi;
import com.dev.insurance_users.generated.client.api.VehiclesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    private final PolicyRepository policyRepository;
    private final ThirdVehiclesApi thirdVehiclesApi;
    private final ThirdUsersApi thirdUsersApi;
    private final UsersApi usersApi;
    private final VehiclesApi vehiclesApi;

    public void save(Part part) {
        // Validar que la póliza existe
        var policy = policyRepository.findById(part.getPolicyId())
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        // Validar third party vehicle si está presente
        if (part.getThirdPartyVehicleId() != null) {
            var auxThirdVehicle = thirdVehiclesApi.getThirdVehicleById(String.valueOf(part.getThirdPartyVehicleId()));
            if (auxThirdVehicle == null || auxThirdVehicle.getId() == null) {
                throw new RuntimeException("Third party vehicle not found");
            }
        }

        // Validar third party user si está presente
        if (part.getThirdPartyId() != null) {
            var auxThirdUser = thirdUsersApi.findThirdUserById(String.valueOf(part.getThirdPartyId()));
            if (auxThirdUser == null || auxThirdUser.getId() == null) {
                throw new RuntimeException("Third party user not found");
            }
        }
        partRepository.save(part);
    }

    public Optional<Part> findById(Long id) {
        return partRepository.findById(id);
    }

    public void deletePartById(Long id) {
        partRepository.deleteById(id);
    }

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public List<Part> getPartsByPolicyId(String policyId) {
        return partRepository.findAllByPolicyId(policyId);
    }

}
