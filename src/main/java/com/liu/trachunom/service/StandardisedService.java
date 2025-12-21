package com.liu.trachunom.service;

import com.liu.trachunom.entity.StandardisedEntity;
import com.liu.trachunom.repository.StandardisedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StandardisedService {
    private final StandardisedRepository standardisedRepository;
    private final EntityService entityService;

    public StandardisedEntity findById(Long id) {
        return standardisedRepository.findById(id).orElse(null);
    }

    public List<StandardisedEntity> findAll() {
        return standardisedRepository.findAll();
    }

    public void save(StandardisedEntity standardisedEntity) {
        standardisedRepository.save(standardisedEntity);
    }

    @Transactional
    public void delete(Long id) {
        standardisedRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return standardisedRepository.existsById(id);
    }
}
