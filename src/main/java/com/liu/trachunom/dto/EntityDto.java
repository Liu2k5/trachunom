package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityDto {
    private Long id;
//    private StructureDto structure;
//    private PronunciationDto pronunciation;
//    private MeaningDto meaning;
//    private LanguageDto language;
    private Long structureId;
    private Long pronunciationId;
    private Long meaningId;
    private Long languageId;

    private String description;
    private boolean compound;
    private boolean attested;
    private boolean standardised;

    // Derived fields for convenience
    private String hnomString;
    private String qnguString;
    private String explanationsString;
}

