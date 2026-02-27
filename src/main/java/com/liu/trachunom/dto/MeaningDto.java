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
public class MeaningDto {
    private Long id;
    private Long originId;
    private MeaningDto origin;
    private List<ExplanationDto> explanations;
    private String explanationsString;
}

