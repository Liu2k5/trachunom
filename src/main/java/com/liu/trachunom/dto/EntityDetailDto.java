package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<EntityDto> variances;
    private List<ExampleDto> examples;
    private List<EntityDto> compositionComponents;
}

