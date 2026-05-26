package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkDto {
    private Long imageId;
    private Long entityId;

    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    private ImageDto image;
    private String entityString;

}
