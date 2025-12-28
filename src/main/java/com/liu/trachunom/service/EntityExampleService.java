package com.liu.trachunom.service;

import com.liu.trachunom.entity.EntityExample;
import com.liu.trachunom.entity.EntityExampleId;
import com.liu.trachunom.repository.EntityExampleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntityExampleService {

    private final EntityExampleRepository entityExampleRepository;

    public EntityExampleService(EntityExampleRepository entityExampleRepository) {
        this.entityExampleRepository = entityExampleRepository;
    }

    public List<EntityExample> findAll() {
        return entityExampleRepository.findAll();
    }

    public Optional<EntityExample> findById(EntityExampleId id) {
        return entityExampleRepository.findById(id);
    }

    @Transactional
    public EntityExample save(EntityExample entityExample) {
        return entityExampleRepository.save(entityExample);
    }

    @Transactional
    public void deleteById(EntityExampleId id) {
        entityExampleRepository.deleteById(id);
    }

    public List<EntityExample> findByEntityId(Long entityId) {
        return entityExampleRepository.findByEntityId(entityId);
    }

    public List<EntityExample> findByExampleId(Long exampleId) {
        return entityExampleRepository.findByExampleId(exampleId);
    }
}

