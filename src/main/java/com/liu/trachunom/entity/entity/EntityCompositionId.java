package com.liu.trachunom.entity.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityCompositionId implements Serializable {
    private Long parentEntityId;
    private Long childEntityId;
    private Long position;
}
