package com.dev.insurance_policies.infrastructure.repository.jpa;

import com.dev.insurance_policies.infrastructure.entity.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartJpaRepository extends JpaRepository<PartEntity, Long> {

    List<PartEntity> findAll();
    void deleteById(Long id);
    Optional<PartEntity> findById(Long id);

}
