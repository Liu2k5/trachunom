package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleWordDto {
    private Long exampleId;
    private Long entityId;
    private Integer position;
    private EntityDto entity;
}

