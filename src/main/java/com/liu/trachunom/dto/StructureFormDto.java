package com.liu.trachunom.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class StructureFormDto {
    private StructureDto structureDto;
    private List<SubStructureDto> subStructures;

    public StructureFormDto() {
        structureDto = new StructureDto();
        subStructures = new ArrayList<>();
    }
}