package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.VehicleThird;

import java.util.List;

public interface VehicleThirdRepository {
    void deleteThirdVehicleById(Long id);
    VehicleThird findByMatriculaThird(String matricula);
    List<VehicleThird> getAllThirdVehicles();
    VehicleThird getThirdVehicleById(Long id);

    List<Integer> saveThirdVehicle(List<VehicleThird> vehiclesThird);
    void updateThirdVehicle(Long id, VehicleThird vehicleThird);
}
