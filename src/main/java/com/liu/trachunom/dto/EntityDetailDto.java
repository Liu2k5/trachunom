package com.liu.trachunom.dto;

import com.liu.trachunom.entity.entity.EntityX;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityDetailDto {
    private Long id;
    private StructureDto structure;
    private PronunciationDto pronunciation;
    private MeaningDto meaning;
    private LanguageDto language;
    private SourceDto source;
    private String description;
    private boolean compound;
    private boolean attested;
    private boolean standardised;

    // Derived fields
    private String hnomString;
    private String qnguString;
    private String explanationsString;

    // Related entities
    private List<EntityCompositionDto> compositions;
    private List<EntityEvolutionDto> evolutions;
    private List<EntityDto> synonyms;
    private List<EntityDto> variants;
    private List<ExampleDto> examples;
    private List<EntityDto> compositionComponents;
    private List<EntityDto> beingSemanticComponents;
    private List<EntityDto> beingPhoneticComponents;
    private Map<String, List<EntityX>> havingSameSemanticComponents;
    private Map<String, List<EntityX>> havingSamePhoneticComponents;
}

