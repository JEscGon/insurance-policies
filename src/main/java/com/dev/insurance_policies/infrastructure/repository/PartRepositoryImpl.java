package com.dev.insurance_policies.infrastructure.repository;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.application.repository.PartRepository;
import com.dev.insurance_policies.infrastructure.repository.jpa.PartJpaRepository;
import com.dev.insurance_policies.infrastructure.repository.mapper.PartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PartRepositoryImpl implements PartRepository {

    private final PartMapper partMapper;
    private final PartJpaRepository partJpaRepository;

    @Override
    public void save(Part part) {

    }

    @Override
    public Optional<Part> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Part> findAll() {
        return List.of();
    }


}
