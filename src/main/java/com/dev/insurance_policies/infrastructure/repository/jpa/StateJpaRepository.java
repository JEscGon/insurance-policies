package com.dev.insurance_policies.infrastructure.repository.jpa;

import com.dev.insurance_policies.infrastructure.repository.jpa.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateJpaRepository extends JpaRepository<StateEntity, Long> {

    Optional<StateEntity> findById(Long id);

}
