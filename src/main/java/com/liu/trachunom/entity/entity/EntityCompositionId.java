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
public class EntityCompositionId implements Serializable {
    private Long parentEntityId;
    private Long childEntityId;
    private Long position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityCompositionId that = (EntityCompositionId) o;
        return Objects.equals(parentEntityId, that.parentEntityId) &&
               Objects.equals(childEntityId, that.childEntityId) &&
               Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentEntityId, childEntityId, position);
    }
}
