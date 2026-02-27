package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PronunciationDto {
    private Long id;
    private Long quocNguId;
    private String pronunciationString;
    private String characterWithPronunciationsString;
}

