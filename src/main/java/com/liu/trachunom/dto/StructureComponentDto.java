package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructureComponentDto {
//    private StructureComponentId id;
//    private StructureDto structure;
    private Long structureId;
    private String structureCharacterString;
//    private StructureDto structureComponent;
    private Long structureComponentId;
    private String structureComponentCharacterString;
//    private StructureClassificationDto structureClassification;
    private Long classificationId;
    private String classificationDescription;
    private Integer quantity;
    private Long position;
}

