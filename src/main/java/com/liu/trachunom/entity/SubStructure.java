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
@Table(name = "SubStructure")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubStructure {

    @EmbeddedId
    private SubStructureId id;

    @MapsId("structureId")
    @ManyToOne
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @MapsId("subStructureId")
    @ManyToOne
    @JoinColumn(name = "sub_structure_id")
    private Structure subStructure;

    @ManyToOne
    @JoinColumn(name = "classification_id")
    @MapsId("classificationId")
    private StructureClassification structureClassification;
    
    private Integer quantity;

}
