package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructureDescriptionDto {
    private Long id;
    private Long descriptionStructureId;
    private String descriptionStructureCharacterString;
}

