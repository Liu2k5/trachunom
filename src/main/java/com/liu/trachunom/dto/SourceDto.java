package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceDto {
    private Long id;
    private String nameQngu;
    private String nameHnom;
    private String fullNameQngu;
    private String fullNameHnom;
    private String description;
    private String authorQngu;
    private String authorHnom;
    private String writerQngu;
    private String writerHnom;
    private Long styleId;
    private String styleDescription;
    private Integer startYear;
    private Integer endYear;
}
