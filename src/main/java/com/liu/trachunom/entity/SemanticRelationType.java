package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "SemanticRelationType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemanticRelationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String description;
}
