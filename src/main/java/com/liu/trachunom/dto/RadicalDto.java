package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RadicalDto {
    private String id;
    private Integer radicalUnicode;
    private Integer unicode;
    private Integer strokeNumber;

    public String getString() {
        try {
            return new String(Character.toChars(unicode));
        } catch (Exception e) {
            return "";
        }
    }
}
