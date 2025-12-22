package com.liu.trachunom.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class EntityEvolutionId {
    private Long fromEntityId;
    private Long toEntityId;
}
