package com.dev.insurance_policies.infrastructure.repository.jpa;

import com.dev.insurance_policies.infrastructure.repository.jpa.entity.PartThirdPartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartThirdPartyJpaRepository extends JpaRepository<PartThirdPartyEntity, Long> {




}
