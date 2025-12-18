package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@jakarta.persistence.Entity
@Table(name = "Entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityX {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @ManyToOne
    @JoinColumn(name = "pronunciation_id")
    private Pronunciation pronunciation;

    @ManyToOne
    @JoinColumn(name = "meaning_id")
    private Meaning meaning;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Nationalized
    private String description;

}
