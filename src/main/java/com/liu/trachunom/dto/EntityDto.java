package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EntityDto {
    private Long id;
    private Long meaningId;
    private Long structureId;
    private Long pronunciationId;
    private Long languageId;
    private String description;
}
