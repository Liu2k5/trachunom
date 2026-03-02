package com.liu.trachunom.entity.example;

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
public class ExampleWordId implements Serializable {
    private Long exampleId;
    private Long entityId;
    private Long position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleWordId that = (ExampleWordId) o;
        return Objects.equals(exampleId, that.exampleId) &&
               Objects.equals(entityId, that.entityId) &&
               Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exampleId, entityId, position);
    }
}
