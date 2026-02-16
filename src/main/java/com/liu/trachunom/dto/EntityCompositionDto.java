package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityCompositionDto {
    private Long parentEntityId;
    private Long childEntityId;
    private EntityDto childEntity;
    private Integer position;
}
