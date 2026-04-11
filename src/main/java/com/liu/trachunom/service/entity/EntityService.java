package com.liu.trachunom.service.entity;

import com.liu.trachunom.entity.character.CharacterX;
import com.liu.trachunom.entity.entity.EntityComposition;
import com.liu.trachunom.entity.entity.EntityX;
import com.liu.trachunom.entity.meaning.Explanation;
import com.liu.trachunom.entity.meaning.Meaning;
import com.liu.trachunom.entity.pronunciation.Pronunciation;
import com.liu.trachunom.entity.structure.Structure;
import com.liu.trachunom.entity.structure.StructureComponent;
import com.liu.trachunom.repository.StructureComponentRepository;
import com.liu.trachunom.service.meaning.MeaningService;
import com.liu.trachunom.service.structure.StructureClassificationService;
import com.liu.trachunom.service.structure.StructureService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.liu.trachunom.repository.EntityRepository;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class EntityService extends ListRepositoryService<EntityX, Long, EntityRepository> {
    private final EntityRepository entityRepository;
    private final EntityCompositionService entityCompositionService;
    private final MeaningService meaningService;
    private final StructureService structureService;
    private final StructureComponentRepository structureComponentRepository;
    private final StructureClassificationService structureClassificationService;

    public EntityX findById(Long id) {
        return entityRepository.findById(id).orElse(null);
    }

    public void save(EntityX entity) {
        entityRepository.save(entity);
        if (entity.isStandardised()) {
            List<EntityX> variances = findVariances(entity);
            for (EntityX variance : variances) {
                if (!variance.getId().equals(entity.getId())) {
                    variance.setStandardised(false);
                    entityRepository.save(variance);
                }
            }
        }
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
                    return o1.getPronunciationString().compareTo(o2.getPronunciationString());
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
                return structureService.getCharacterStringById(entity.getStructure().getId());
            } catch (Exception e) {
                return "(trống)";
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
            return hnomString.toString().isEmpty() ? "(trống)": hnomString.toString();
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
                return qnguString.toString().trim().isEmpty() ? "(trống)" : qnguString.toString().trim();
            }
            return "(trống)";
        }
    }

    public List<EntityX> findByQuery(String query) {
        List<EntityX> results =
        findAll().stream()
            .filter(entity ->
                getHnomStringById(entity.getId()).equals(query) || getQnguStringById(entity.getId()).equalsIgnoreCase(query)
            )
            .collect(Collectors.toList());
        results
        .addAll(
            findAll().stream()
            .filter(entity ->
                getQnguStringById(entity.getId()).contains(query) ||
                getHnomStringById(entity.getId()).toLowerCase().contains(query.toLowerCase()))
            .toList()
        );
//        results
//        .addAll(
//        findAll().stream()
//                .filter(entity -> entity.getMeaning() != null)
//                .filter(entity ->
//                        entity.getMeaning().getExplanations().stream()
//                        .anyMatch(explanation -> explanation.getDescription().toLowerCase().contains(query.toLowerCase())))
//                .toList()
//        );
        return results.stream().distinct().limit(50).collect(Collectors.toList());
    }

    public List<EntityX> findSynonyms(EntityX entity) {
        if (entity.getMeaning() == null) {
            return List.of();
        }

        List<Explanation> explanations = entity.getMeaning().getExplanations();
        List<Meaning> meanings = meaningService.findAll()
                .stream()
                .filter(meaning -> meaning.getExplanations().stream().anyMatch(explanations::contains))
                .toList();
        return findByStandardised(true).stream()
                .filter(entityX -> meanings.contains(entityX.getMeaning())
                        && !entityX.getId().equals(entity.getId())
                        && !entityX.getPronunciationString().equals(entity.getPronunciationString())
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

    public List<EntityX> findByPronunciation(Pronunciation entity) {
        return entityRepository.findByPronunciation(entity);
    }

    public List<EntityX> findByStructure(Structure structure) {
        return entityRepository.findByStructure(structure);
    }

    public List<EntityX> findByPronunciationId(Long pronunciationId) {
        return entityRepository.findByPronunciation_Id(pronunciationId);
    }

    public List<EntityX> findBeingSematicOrPhoneticComponentByEntityId(Long entityId, boolean isSemantic) {
        EntityX entity = findById(entityId);
        if (
                entity == null ||
                entity.isCompound() ||
                entity.getStructure() == null ||
                entity.getStructure().getCharacter() == null
        ) return List.of();

        List<StructureComponent> structureComponents =
                structureComponentRepository.findByStructureComponent(entity.getStructure()).stream()
                        .filter(sc -> {
                            return isSemantic ? structureClassificationService.isSemanticClassification(sc.getStructureClassification()) :
                            structureClassificationService.isPhoneticClassification(sc.getStructureClassification());
                        })
                        .toList();
        return structureComponents.stream()
                .map(StructureComponent::getStructure)
                .map(entityRepository::findByStructure)
                .filter(entities -> !entities.isEmpty())
//                .flatMap(Collection::stream)
                .map(List::getFirst) // Assuming one entity per structure, adjust if necessary
                .filter(e -> !e.getId().equals(entityId))
                .distinct()
                .toList();
    }

    public Map<String, List<EntityX>> findHavingSameSemanticOrPhoneticComponentByEntityId(Long entityId, boolean isSemantic) {
        EntityX entity = findById(entityId);
        if (
                entity == null ||
                entity.isCompound() ||
                entity.getStructure() == null ||
                entity.getStructure().getCharacter() == null
        ) return Map.of();

        // get structure components belonging to the structure of the entity, filter by semantic or phonetic classification
        List<StructureComponent> structureComponents = structureComponentRepository.findByStructure(entity.getStructure()).stream()
                .filter(sc -> {
                    return isSemantic ? structureClassificationService.isSemanticClassification(sc.getStructureClassification()) :
                            structureClassificationService.isPhoneticClassification(sc.getStructureClassification());
                })
                .collect(Collectors.toList());

        // allow to find entities having the same phonetic component recursively
        if (!isSemantic) {
            List<StructureComponent> temp = new ArrayList<>(structureComponents);
            Queue<StructureComponent> queue = new LinkedList<>(temp);
            queue.addAll(structureComponents);
            while (!queue.isEmpty()) {
                StructureComponent sc = queue.poll();
                List<StructureComponent> relatedComponents = structureComponentRepository.findByStructure(sc.getStructureComponent()).stream()
                        .filter(s -> {
                            return structureClassificationService.isPhoneticClassification(s.getStructureClassification());
                        })
                        .toList();
                for (StructureComponent related : relatedComponents) {
                    if (!temp.contains(related)) {
                        temp.add(related);
                        queue.add(related);
                    }
                }
            }
            structureComponents = temp;
        }
        structureComponents = structureComponents.stream().distinct().toList();

        // fetch entities having the same semantic or phonetic component, group by the component structure
        Map<String, List<EntityX>> result = new HashMap<>();
        for (StructureComponent sc : structureComponents) {
            List<StructureComponent> relatedComponents = structureComponentRepository.findByStructureComponent(sc.getStructureComponent());
            List<EntityX> relatedEntities = relatedComponents.stream()
                    .map(StructureComponent::getStructure)
                    .map(entityRepository::findByStructure)
                    .filter(entities -> !entities.isEmpty())
                    .map(List::getFirst) // Assuming one entity per structure, adjust if necessary
                    .filter(e -> !e.getId().equals(entityId))
                    .distinct()
                    .toList();
            if (!relatedEntities.isEmpty()) {
                result.put(sc.getStructureComponent().getCharacter().getString(), relatedEntities);
            }
        }
        return result;
    }


}
