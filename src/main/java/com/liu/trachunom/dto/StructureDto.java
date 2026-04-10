package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructureDto {
    private Long id;
    private CharacterDto character;
    private Long structureTypeId;
    private String characterString;
    private String characterWithPronunciationsString;
}

