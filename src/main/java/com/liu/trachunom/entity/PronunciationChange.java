package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PronunciationChange")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PronunciationChange {
    @EmbeddedId
    private PronunciationChangeId id;

    @MapsId("pronunciationId")
    @ManyToOne
    @JoinColumn(name = "pronunciation_id")
    private Pronunciation pronunciation;

    @MapsId("previousPronunciationId")
    @ManyToOne
    @JoinColumn(name = "previous_pronunciation_id")
    private Pronunciation previousPronunciation;

}
