package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "EntityType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String description;
}
