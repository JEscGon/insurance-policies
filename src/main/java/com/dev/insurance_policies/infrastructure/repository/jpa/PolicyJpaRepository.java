package com.dev.insurance_policies.infrastructure.repository.jpa;

import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PolicyEntity;
import com.dev.insurance_policies.infrastructure.rest.mapper.PolicyDtoMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PolicyJpaRepository extends JpaRepository<PolicyEntity, Long> {

    List<PolicyEntity> findAll();
    Optional<PolicyEntity> findById(Long id);
    void deleteById(Long id);
    Optional<PolicyEntity> findByUserId(Long userId);
    Optional<PolicyEntity> findByVehicleId(Long vehicleId);
}
