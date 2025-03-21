package com.dev.insurance_policies.infrastructure.repository.jpa;

import com.dev.insurance_policies.infrastructure.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PolicyJpaRepository extends JpaRepository<PolicyEntity, Long> {

    List<PolicyEntity> findAll();
    Optional<PolicyEntity> findById(Long id);
    void deleteById(Long id);

}
