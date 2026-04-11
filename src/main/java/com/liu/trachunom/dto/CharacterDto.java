package com.liu.trachunom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDto {
    private String characterString;
    private RadicalDto radical;
    private Integer additionalStrokeNumber;
    private Integer totalStrokeNumber;

    public String getRadicalString() {
        try {
            return new String(Character.toChars(radical.getRadicalUnicode()));
        } catch (Exception e) {
            return "";
        }
    }

    public String getRadicalStringByUnicode() {
        try {
            return new String(Character.toChars(radical.getUnicode()));
        } catch (Exception e) {
            return "";
        }
    }

}

