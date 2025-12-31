package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pronunciation_evolution")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PronunciationEvolution {
    @EmbeddedId
    private PronunciationEvolutionId id;

    @MapsId("fromPronunciationId")
    @ManyToOne
    @JoinColumn(name = "from_pronunciation_id")
    private Pronunciation fromPronunciation;

    @MapsId("toPronunciationId")
    @ManyToOne
    @JoinColumn(name = "to_pronunciation_id")
    private Pronunciation toPronunciation;
}
