package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.Vehicle;
import com.dev.insurance_policies.application.repository.VehicleRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.VehicleDtoClientMapper;
import com.dev.insurance_users.generated.client.api.VehiclesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VehicleRestClientImpl implements VehicleRepository {

    private final VehiclesApi vehiclesApi;
    private final VehicleDtoClientMapper vehicleDtoClientMapper;

    @Override
    public void deleteVehicleById(Long id) {
        vehiclesApi.deleteVehicleById(id);
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleDtoClientMapper.fromDtoToDomain(vehiclesApi.getVehicleById(id));
    }

    @Override
    public Vehicle findByMatricula(String matricula) {
        return vehicleDtoClientMapper.fromDtoToDomain(vehiclesApi.findByMatricula(matricula));
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        return vehiclesApi.getAllVehicles().stream()
                .map(vehicleDtoClientMapper::fromDtoToDomain)
                .toList();
    }

    @Override
    public List<Vehicle> findByUserId(Long userId) {
        return vehiclesApi.getVehiclesByUserId(userId).stream()
                .map(vehicleDtoClientMapper::fromDtoToDomain)
                .toList();
    }

    @Override
    public void save(Vehicle vehicle) {
        vehiclesApi.saveVehicle(vehicleDtoClientMapper.fromDomainToDto(vehicle));
    }

    @Override
    public void updateVehicle(Long id, Vehicle vehicle) {
        vehiclesApi.updateVehicle(id, vehicleDtoClientMapper.fromDomainToDto(vehicle));
    }

}
