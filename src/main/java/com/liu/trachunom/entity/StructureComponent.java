package com.liu.trachunom.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name = "classification_id")
    @MapsId("classificationId")
    private StructureClassification structureClassification;
    
    private Integer quantity;

}
