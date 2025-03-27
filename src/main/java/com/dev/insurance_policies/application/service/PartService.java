package com.dev.insurance_policies.application.service;

import com.dev.insurance_policies.application.domain.Part;
import com.dev.insurance_policies.application.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;

    public void save(Part part) {
        partRepository.save(part);
    }

    public Optional<Part> findById(Long id) {
        return partRepository.findById(id);
    }

    public void deletePartById(Long id) {
        partRepository.deleteById(id);
    }

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

}
