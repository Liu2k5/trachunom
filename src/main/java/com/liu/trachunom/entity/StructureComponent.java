package com.liu.trachunom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "structure_component")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StructureComponent {

    @EmbeddedId
    private StructureComponentId id;

    @MapsId("structureId")
    @ManyToOne
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @MapsId("structureComponentId")
    @ManyToOne
    @JoinColumn(name = "structure_component_id")
    private Structure structureComponent;

    @MapsId("classificationId")
    @ManyToOne
    @JoinColumn(name = "classification_id")
    private StructureClassification structureClassification;
    
    private Integer quantity;

}
