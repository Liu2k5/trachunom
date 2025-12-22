package com.liu.trachunom.service;

import com.liu.trachunom.entity.CharacterX;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.EntityX;
import com.liu.trachunom.repository.EntityRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityService {
    private final EntityRepository entityRepository;
    private final ExplanationService explanationService;
    private final StructureService structureService;
    private final PronunciationService pronunciationService;
    private final LanguageService languageService;

    public EntityX findById(Long id) {
        return entityRepository.findById(id).orElse(null);
    }

    public void save(EntityX entity) {
        entityRepository.save(entity);
    }

    public List<EntityX> findByCharacter(CharacterX character) {
        return entityRepository.findByStructure_Character(character);
    }

    public boolean existsById(Long entityId) {
        return entityRepository.existsById(entityId);
    }

    public List<EntityX> findAll() {
        return entityRepository.findAll();
    }

    public void deleteById(Long id) {
        entityRepository.deleteById(id);
    }

    public List<EntityX> findByCompound(boolean b) {
        return entityRepository.findByCompound(b);
    }
}
