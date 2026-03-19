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
public class EntityEvolutionId implements Serializable {
    private Long fromEntityId;
    private Long toEntityId;
    private Long descriptionId;
}
