package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "pronunciation_classification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PronunciationClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
}
