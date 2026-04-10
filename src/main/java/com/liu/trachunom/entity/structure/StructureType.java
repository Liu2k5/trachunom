package com.liu.trachunom.entity.structure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "structure_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StructureType {
    @Id
    private Long id;
    private String description;
}
