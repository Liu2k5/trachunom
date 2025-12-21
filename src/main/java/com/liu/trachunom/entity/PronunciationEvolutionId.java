package com.liu.trachunom.entity;

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
public class PronunciationEvolutionId implements Serializable {
    private Long fromPronunciationId;
    private Long toPronunciationId;
    private Long pronunciationClassificationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PronunciationEvolutionId that = (PronunciationEvolutionId) o;
        return Objects.equals(fromPronunciationId, that.fromPronunciationId) &&
               Objects.equals(toPronunciationId, that.toPronunciationId) &&
               Objects.equals(pronunciationClassificationId, that.pronunciationClassificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromPronunciationId, toPronunciationId, pronunciationClassificationId);
    }
}
