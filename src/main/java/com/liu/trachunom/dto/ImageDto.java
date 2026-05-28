package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Long id;
    private Long sourceId;
    private Integer page;
    private String link;

    private String sourceNameQngu;
    private String sourceNameHnom;
}
