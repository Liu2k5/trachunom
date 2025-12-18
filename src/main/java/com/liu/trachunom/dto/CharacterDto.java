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
public class CharacterDto {
    private String character;
    private String radicalId;
    private Integer additionalStrokeNumber;
    private Integer totalStrokeNumber;
}
