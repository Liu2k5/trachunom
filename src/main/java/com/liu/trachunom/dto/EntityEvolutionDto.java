package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityEvolutionDto {
    private Long fromEntityId;
    private Long toEntityId;
    private EntityDto fromEntity;
    private Long descriptionId;
    private String description;
}

