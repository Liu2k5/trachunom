package com.liu.trachunom.service;

import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.entity.EntityComposition;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.EntityX;
import com.liu.trachunom.repository.EntityRepository;

import lombok.RequiredArgsConstructor;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EntityService {
    private final EntityRepository entityRepository;
    private final ExplanationService explanationService;
    private final StructureService structureService;
    private final PronunciationService pronunciationService;
    private final LanguageService languageService;
    private final EntityCompositionService entityCompositionService;

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

    public String getHnomString(EntityX entity) {
        try {
            return entity.getStructure().getCharacter().getString();
            // ca thuc the don va kep deu co the co 1 ki tu han nom dai dien
        } catch (Exception e) {
            if (entity.isCompound()) {
                List<EntityComposition> entityCompositions = entityCompositionService.findByParentEntityId(entity.getId());
                StringBuilder hnomString = new StringBuilder();
                for (EntityComposition ec : entityCompositions) {
                    hnomString.append(ec.getChildEntity().getStructure().getCharacter().getString());
                }
                return hnomString.toString();
            }
            return "";
        }
    }

    public String getQnguString(EntityX entity) {
        try {
            return entity.getPronunciation().getString();
        } catch (Exception e) {
            if (entity.isCompound()) {
                List<EntityComposition> entityCompositions = entityCompositionService.findByParentEntityId(entity.getId());
                StringBuilder qnguString = new StringBuilder();
                for (EntityComposition ec : entityCompositions) {
                    qnguString.append(ec.getChildEntity().getPronunciation().getString());
                    qnguString.append(" ");
                }
                return qnguString.toString().trim();
            }
            return "";
        }
    }

    public List<EntityX> findByQuery(String query) {
        return entityRepository.findAll().stream()
                .filter(entity -> getHnomString(entity).contains(query) || getQnguString(entity).contains(query))
                .toList();
    }
}
