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
public class SemanticRelationId implements Serializable {
    private Long fromMeaningId;
    private Long toMeaningId;
    private Long semanticRelationTypeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemanticRelationId that = (SemanticRelationId) o;
        return Objects.equals(fromMeaningId, that.fromMeaningId) &&
               Objects.equals(toMeaningId, that.toMeaningId) &&
               Objects.equals(semanticRelationTypeId, that.semanticRelationTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromMeaningId, toMeaningId, semanticRelationTypeId);
    }
}
