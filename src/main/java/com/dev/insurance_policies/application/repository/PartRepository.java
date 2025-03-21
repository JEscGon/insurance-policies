package com.dev.insurance_policies.application.repository;

import com.dev.insurance_policies.application.domain.Part;

import java.util.List;
import java.util.Optional;

public interface PartRepository {

    public void save(Part part);
    public Optional<Part> findById(Long id);
    public void deleteById(Long id);
    public List<Part> findAll();

}
