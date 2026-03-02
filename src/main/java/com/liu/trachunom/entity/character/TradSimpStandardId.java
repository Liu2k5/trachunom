package com.liu.trachunom.entity.character;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradSimpStandardId implements Serializable {
    private Integer traditionalUnicode;
    private Integer simplifiedUnicode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradSimpStandardId that = (TradSimpStandardId) o;
        return Objects.equals(traditionalUnicode, that.traditionalUnicode) &&
               Objects.equals(simplifiedUnicode, that.simplifiedUnicode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traditionalUnicode, simplifiedUnicode);
    }
}
