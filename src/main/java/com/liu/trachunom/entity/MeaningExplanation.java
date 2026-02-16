package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "meaning_explanation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeaningExplanation {
    @EmbeddedId
    private MeaningExplanationId id;

    @MapsId("meaningId")
    @ManyToOne
    @JoinColumn(name = "meaning_id")
    private Meaning meaning;

    @MapsId("explanationId")
    @ManyToOne
    @JoinColumn(name = "explanation_id")
    private Explanation explanation;

}
