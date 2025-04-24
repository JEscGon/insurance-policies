package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.Vehicle;

import java.util.List;

public interface VehicleRepository {

    void deleteVehicleById(Long id);

    Vehicle findById(Long id);

    Vehicle findByMatricula(String matricula);

    List<Vehicle> findAllVehicles();

    List<Vehicle> findByUserId(Long userId);

    void save(Vehicle vehicle);

    void updateVehicle(Long id, Vehicle vehicle);

}
