package com.liu.trachunom.service.entity;

import com.liu.trachunom.entity.entity.EntityComposition;
import com.liu.trachunom.entity.entity.EntityCompositionId;
import com.liu.trachunom.repository.EntityCompositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityCompositionService {
    private final EntityCompositionRepository entityCompositionRepository;

    public List<EntityComposition> findByParentEntityId(Long id) {
        return entityCompositionRepository.findByIdParentEntityId(id);
    }

    public void save(EntityComposition composition) {
        entityCompositionRepository.save(composition);
    }

    public void deleteById(EntityCompositionId id) {
        entityCompositionRepository.deleteById(id);
    }
}
