package com.liu.trachunom.entity.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evolution_description")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvolutionDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
}
