package com.liu.trachunom.service;

import com.liu.trachunom.entity.EntityEvolution;
import com.liu.trachunom.entity.EntityEvolutionId;
import com.liu.trachunom.repository.EntityEvolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntityEvolutionService {
    @Autowired
    private EntityEvolutionRepository entityEvolutionRepository;

    @Transactional(readOnly = true)
    public List<EntityEvolution> findAll() {
        return entityEvolutionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<EntityEvolution> findById(EntityEvolutionId id) {
        return entityEvolutionRepository.findById(id);
    }

    @Transactional
    public EntityEvolution save(EntityEvolution entityEvolution) {
        return entityEvolutionRepository.save(entityEvolution);
    }

    @Transactional
    public void delete(EntityEvolution entityEvolution) {
        entityEvolutionRepository.delete(entityEvolution);
    }

    @Transactional
    public void deleteById(EntityEvolutionId id) {
        entityEvolutionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EntityEvolution> findByFromEntityId(Long fromEntityId) {
        return entityEvolutionRepository.findByFromEntityId(fromEntityId);
    }

    @Transactional(readOnly = true)
    public List<EntityEvolution> findByToEntityId(Long toEntityId) {
        return entityEvolutionRepository.findByToEntityId(toEntityId);
    }
}

