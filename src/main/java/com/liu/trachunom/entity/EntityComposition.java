package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entity_composition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityComposition {
    @EmbeddedId
    private EntityCompositionId id;

    @MapsId("parentEntityId")
    @ManyToOne
    @JoinColumn(name = "parent_entity_id")
    private EntityX parentEntity;

    @MapsId("childEntityId")
    @ManyToOne
    @JoinColumn(name = "child_entity_id")
    private EntityX childEntity;

}
