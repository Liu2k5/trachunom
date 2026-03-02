package com.liu.trachunom.entity.entity;

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
public class EntityEvolutionId implements Serializable {
    private Long fromEntityId;
    private Long toEntityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityEvolutionId that = (EntityEvolutionId) o;
        return Objects.equals(fromEntityId, that.fromEntityId) &&
                Objects.equals(toEntityId, that.toEntityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromEntityId, toEntityId);
    }
}
