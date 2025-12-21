package com.liu.trachunom.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class StructureComponentId implements Serializable {
    private Long structureId;
    private Long structureComponentId;
    private Long classificationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructureComponentId that = (StructureComponentId) o;
        return Objects.equals(structureId, that.structureId) &&
               Objects.equals(structureComponentId, that.structureComponentId) &&
               Objects.equals(classificationId, that.classificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId, structureComponentId, classificationId);
    }
}
