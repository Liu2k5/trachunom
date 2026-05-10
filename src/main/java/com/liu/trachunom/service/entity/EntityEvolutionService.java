package com.liu.trachunom.service.entity;

import com.liu.trachunom.entity.entity.EntityEvolution;
import com.liu.trachunom.entity.entity.EntityEvolutionId;
import com.liu.trachunom.repository.EntityEvolutionRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@BrowserCallable
@AnonymousAllowed
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

    @Transactional
    public void deleteByEachId(Long fromEntityId, Long toEntityId, Long descriptionId) {
        entityEvolutionRepository.deleteById_FromEntityIdAndId_ToEntityIdAndId_DescriptionId(fromEntityId, toEntityId, descriptionId);
    }
}

