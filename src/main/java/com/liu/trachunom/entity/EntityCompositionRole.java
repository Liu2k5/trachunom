package com.liu.trachunom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "EntityCompositionRole")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityCompositionRole {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String description;
}
