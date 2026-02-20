package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PronunciationEvolutionDto {
    private Long fromPronunciationId;
    private Long toPronunciationId;
    private String fromPronunciationString;
    private String toPronunciationString;
}
