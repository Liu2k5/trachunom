package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "entity_evolution")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityEvolution {
    @EmbeddedId
    private EntityEvolutionId id;

    @MapsId("fromEntityId")
    @ManyToOne
    @JoinColumn(name = "from_entity_id")
    private EntityX fromEntity;

    @MapsId("toEntityId")
    @ManyToOne
    @JoinColumn(name = "to_entity_id")
    private EntityX toEntity;

    private String description;

}
