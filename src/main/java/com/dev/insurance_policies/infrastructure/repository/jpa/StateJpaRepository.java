package com.dev.insurance_policies.infrastructure.repository.jpa;

import com.dev.insurance_policies.infrastructure.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateJpaRepository extends JpaRepository<StateEntity, Long> {

    List<StateEntity> findAll();
    Optional<StateEntity> findById(Long id);
    void deleteById(Long id);

}
