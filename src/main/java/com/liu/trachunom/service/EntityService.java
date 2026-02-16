package com.liu.trachunom.service;

import com.liu.trachunom.entity.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;

import com.liu.trachunom.repository.EntityRepository;

import lombok.RequiredArgsConstructor;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class EntityService extends ListRepositoryService<EntityX, Long, EntityRepository> {
    private final EntityRepository entityRepository;
    private final EntityCompositionService entityCompositionService;
    private final MeaningService meaningService;

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
        return entityRepository.findAll().stream()
                .sorted((o1, o2) -> {
                    if (o1.isStandardised() && !o2.isStandardised()) {
                        return -1;
                    }
                    if (!o1.isStandardised() && o2.isStandardised()) {
                        return 1;
                    }
                    return o1.getId().compareTo(o2.getId());
                })
                .toList();
    }

    public void deleteById(Long id) {
        entityRepository.deleteById(id);
    }

    public List<EntityX> findByCompound(boolean b) {
        return entityRepository.findByCompound(b);
    }

    public String getHnomStringById(Long entityId) {
        EntityX entity = findById(entityId);

        List<EntityComposition> entityCompositions = entityCompositionService.findByParentEntityId(entity.getId());
        if (entityCompositions.isEmpty()) {
            try {
                return entity.getStructure().getCharacterString();
            } catch (Exception e) {
                return "an error occurred";
            }
        } else {
            StringBuilder hnomString = new StringBuilder();
            for (EntityComposition ec : entityCompositions) {
                try {
                    hnomString.append(ec.getChildEntity().getStructure().getCharacter().getString());
                } catch (Exception e) {
                    // Nếu có lỗi, bỏ qua và tiếp tục
                }
            }
            if (!entity.isCompound()) {
                return "[" + hnomString.toString() + "]";
            }
            return hnomString.toString();
        }
    }

    public String getQnguStringById(Long entityId) {
        EntityX entity = findById(entityId);

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
        List<EntityX> results =
        entityRepository.findAll().stream()
            .filter(entity ->
                getHnomStringById(entity.getId()).equals(query) || getQnguStringById(entity.getId()).toLowerCase().contains(query.toLowerCase())
            )
            .collect(Collectors.toList());
        results
        .addAll(
        entityRepository.findAll().stream()
                .filter(entity -> entity.getMeaning() != null)
                .filter(entity ->
                        entity.getMeaning().getExplanations().stream()
                        .anyMatch(explanation -> explanation.getDescription().toLowerCase().contains(query.toLowerCase())))
                .toList()
        );
        return results.stream().distinct().collect(Collectors.toList());
    }

    public List<EntityX> findSynonyms(EntityX entity) {
        List<Explanation> explanations = entity.getMeaning().getExplanations();
        List<Meaning> meanings = meaningService.findAll()
                .stream()
                .filter(meaning -> meaning.getExplanations().stream().anyMatch(explanations::contains))
                .toList();
        return findByStandardised(true).stream()
                .filter(entityX -> meanings.contains(entityX.getMeaning())
                        && !entityX.getId().equals(entity.getId())
                        && !entityX.getPronunciationString().equals(entity.getPronunciationString())
//                        && !entityX.getMeaning().getId().equals(entity.getMeaning().getId())
                        // so sanh pronunciation + meaning de tranh di the
                        && entityX.isAttested())
                .toList();
    }

    private List<EntityX> findByStandardised(boolean b) {
        return entityRepository.findByStandardised(b);
    }

    public EntityX findStandardByEntity(EntityX entity) {
        EntityX foundEntity = entityRepository.findFirstByLanguageAndPronunciationAndMeaningAndStandardised(
                entity.getLanguage(),
                entity.getPronunciation(),
                entity.getMeaning(),
                true
        );
        if (foundEntity == null)  return null;
        if (!foundEntity.getId().equals(entity.getId())) return foundEntity;
        return null;
    }

    public List<EntityX> findVariances(EntityX entity) {
        return findAll().stream()
                .filter((EntityX entityX) -> !entityX.isCompound() // di the khong phai tu ghep
                && !entityX.getId().equals(entity.getId())
                && entity.getPronunciation() != null
                && entityX.getPronunciation() != null
                && entityX.getPronunciation().getId().equals(entity.getPronunciation().getId())
                && entityX.getMeaning().getId().equals(entity.getMeaning().getId()))
                .toList();
    }
}
