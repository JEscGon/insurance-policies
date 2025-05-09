package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.VehicleThird;
import com.dev.insurance_policies.application.repository.VehicleThirdRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.VehicleThirdDtoClientMapper;
import com.dev.insurance_users.generated.client.api.ThirdVehiclesApi;
import com.dev.insurance_users.generated.client.model.ThirdPartyVehiclesWrapperClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VehicleThirdRestClientImpl implements VehicleThirdRepository {

    private final ThirdVehiclesApi thirdVehiclesApi;
    private final VehicleThirdDtoClientMapper vehicleThirdDtoClientMapper;

    @Override
    public void deleteThirdVehicleById(Long id) {
        thirdVehiclesApi.deleteThirdVehicleById(id);
    }
    @Override
    public VehicleThird findByMatriculaThird(String matricula) {
        return vehicleThirdDtoClientMapper.fromDtoToDomain(thirdVehiclesApi.findByMatriculaThird(matricula));
    }
    @Override
    public List<VehicleThird> getAllThirdVehicles() {
        return thirdVehiclesApi.getAllThirdVehicles().getVehicles().stream()
                .map(vehicleThirdDtoClientMapper::fromDtoToDomain)
                .toList();
    }
    @Override
    public VehicleThird getThirdVehicleById(Long id) {
        return vehicleThirdDtoClientMapper.fromDtoToDomain(thirdVehiclesApi.getThirdVehicleById(id));
    }

    @Override
    public void saveThirdVehicle(VehicleThird vehicleThird) {
        var vehicleWrapper = new ThirdPartyVehiclesWrapperClientDto();
        vehicleWrapper.addVehiclesItem(vehicleThirdDtoClientMapper.fromDomainToDto(vehicleThird));
        thirdVehiclesApi.saveThirdVehicle(vehicleWrapper);
    }
    @Override
    public void updateThirdVehicle(Long id, VehicleThird vehicleThird) {
        thirdVehiclesApi.updateThirdVehicle(id, vehicleThirdDtoClientMapper.fromDomainToDto(vehicleThird));
    }

}
