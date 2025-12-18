package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Etymology")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etymology {
    @EmbeddedId
    private EtymologyId id;

    @MapsId("meaningId")
    @ManyToOne
    @JoinColumn(name = "meaning_id")
    private Meaning meaning;

    @MapsId("originId")
    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Meaning origin;
}
