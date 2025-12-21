package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SemanticRelation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemanticRelation {
    @EmbeddedId
    private SemanticRelationId id;

    @MapsId("fromMeaningId")
    @ManyToOne
    @JoinColumn(name = "from_meaning_id")
    private Meaning fromMeaning;

    @MapsId("toMeaningId")
    @ManyToOne
    @JoinColumn(name = "to_meaning_id")
    private Meaning toMeaning;

    @MapsId("semanticRelationTypeId")
    @ManyToOne
    @JoinColumn(name = "semantic_relation_type_id")
    private SemanticRelationType semanticRelationType;
}
