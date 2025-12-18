package com.liu.trachunom.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradSimpStandardId {
    private Integer traditionalUnicode;
    private Integer simplifiedUnicode;
}
