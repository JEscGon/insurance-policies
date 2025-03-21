package com.dev.insurance_policies.infrastructure.repository.jpa;

import com.dev.insurance_policies.infrastructure.entity.PolicyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PolicyTypeJpaRepository extends JpaRepository<PolicyTypeEntity, Long> {

    List<PolicyTypeEntity> findAll();
    Optional<PolicyTypeEntity> findById(Long id);
    void deleteById(Long id);

}
