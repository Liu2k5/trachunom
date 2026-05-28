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
public class ExampleDto {
    private Long id;
    private String hnomString;
    private String qnguString;
    private List<ExampleWordDto> exampleWords;
    private String sourceNameQngu;
    private String sourceNameHnom;
    private String sourceDescription;
    private String sourceAuthorQngu;
    private String sourceAuthorHnom;
    private String sourceWriterQngu;
    private String sourceWriterHnom;
    private String sourceStyleDescription;
    private Long sourceId;
}

