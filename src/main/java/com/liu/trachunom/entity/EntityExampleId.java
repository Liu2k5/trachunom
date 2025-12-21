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
public class EntityExampleId implements Serializable {
    private Long entityId;
    private Long exampleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityExampleId that = (EntityExampleId) o;
        return Objects.equals(entityId, that.entityId) &&
               Objects.equals(exampleId, that.exampleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityId, exampleId);
    }
}
