package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.*;
import com.liu.trachunom.entity.*;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class EntityDetailEndpoint {
    private final EntityService entityService;
    private final EntityMapper entityMapper;
    private final EntityCompositionService entityCompositionService;
    private final EntityEvolutionService entityEvolutionService;
    private final ExampleService exampleService;
    private final StructureService structureService;
    private final StructureComponentService structureComponentService;
    private final ExampleWordService exampleWordService;

    public EntityDetailDto getEntityDetail(Long id) {
        EntityX entity = entityService.findById(id);
        if (entity == null) {
            return null;
        }

        EntityDetailDto dto = EntityDetailDto.builder()
                .id(entity.getId())
                .structure(entityMapper.toStructureDto(entity.getStructure()))
                .pronunciation(entityMapper.toPronunciationDto(entity.getPronunciation()))
                .meaning(entityMapper.toMeaningDto(entity.getMeaning()))
                .language(entityMapper.toLanguageDto(entity.getLanguage()))
                .description(entity.getDescription())
                .compound(entity.isCompound())
                .attested(entity.isAttested())
                .standardised(entity.isStandardised())
                .hnomString(entityService.getHnomStringById(entity.getId()))
                .qnguString(entityService.getQnguStringById(entity.getId()))
                .explanationsString(entity.getExplanationsString())
                .examples(
                        exampleService.findByEntityId(entity.getId()).stream()
                                .map(entityMapper::toExampleDto)
                                .collect(Collectors.toList())
                )
                .compositionComponents(
                        entityCompositionService.findByParentEntityId(entity.getId())
                        .stream()
                        .map(EntityComposition::getChildEntity)
                        .map(entityMapper::toEntityDto)
                        .collect(Collectors.toList()))
                .build();

        // Get compositions for compound entities
        if (entity.isCompound()) {
            List<EntityComposition> compositions = entityCompositionService.findByParentEntityId(id);
            dto.setCompositions(compositions.stream()
                    .map(entityMapper::toEntityCompositionDto)
                    .collect(Collectors.toList()));
        }

        // Get entity evolutions
        List<EntityEvolution> evolutions = entityEvolutionService.findByToEntityId(id);
        if (!evolutions.isEmpty()) {
            dto.setEvolutions(evolutions.stream()
                    .map(entityMapper::toEntityEvolutionDto)
                    .collect(Collectors.toList()));
        }

        // Get synonyms
        List<EntityX> synonyms = entityService.findSynonyms(entity);
        dto.setSynonyms(entityMapper.toEntityDtoList(synonyms));

        // Get variances
        List<EntityX> variances = entityService.findVariances(entity);
        dto.setVariances(entityMapper.toEntityDtoList(variances));

        // Get examples
        List<Example> examples = exampleService.findByEntityId(id);
        if (!examples.isEmpty()) {
            dto.setExamples(examples.stream()
                    .map(entityMapper::toExampleDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public StructureDto getStructureDtoByStructureId(Long structureId) {
        Structure structure = structureService.findById(structureId);
        if (structure == null) {
            return null;
        }
        return entityMapper.toStructureDto(structure);
    }

    public List<EntityEvolutionDto> getEntityEvolutionsByToEntityId(Long toEntityId) {
        List<EntityEvolution> evolutions = entityEvolutionService.findByToEntityId(toEntityId);
        return evolutions.stream()
                .map(entityMapper::toEntityEvolutionDto)
                .collect(Collectors.toList());
    }

    public EntityDto getEntityDtoByEntityId(Long entityId) {
        EntityX entity = entityService.findById(entityId);
        return entityMapper.toEntityDto(entity);
    }

    public List<EntityDto> getVariancesByEntityId(Long entityId) {
        EntityX entity = entityService.findById(entityId);
        List<EntityX> variances = entityService.findVariances(entity);
        return entityMapper.toEntityDtoList(variances);
    }

    public List<EntityDto> getSynonymsByEntityId(Long entityId) {
        EntityX entity = entityService.findById(entityId);
        List<EntityX> synonyms = entityService.findSynonyms(entity);
        return entityMapper.toEntityDtoList(synonyms);
    }

    public int calcFractionByStructureId(Long id) {
        Structure structure = structureService.findById(id);
        if (structure == null) {
            return 1;
        }
        List<StructureComponent> components = structureComponentService.getStructureComponents(structure);
        if (components.isEmpty()) {
            return 1;
        }
        int fraction = 0;
        for (StructureComponent component : components) {
            fraction += calcFractionByStructureId(component.getStructureComponent().getId());
        }
        return fraction;
    }

    public String getGridTemplateColumnsByStructureId(Long id) {
        Structure structure = structureService.findById(id);
        if (structure == null) {
            return "1fr";
        }
        List<StructureComponent> components = structureComponentService.getStructureComponents(structure);
        if (components.isEmpty()) {
            return "1fr";
        }
        StringBuilder templateColumns = new StringBuilder();
        for (StructureComponent component : components) {
            templateColumns.append(calcFractionByStructureId(component.getStructureComponent().getId())).append("fr ");
        }
        return templateColumns.toString().trim();
    }
}

