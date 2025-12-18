package com.liu.trachunom.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "ComplexSingleEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplexSingleEntity {
    @EmbeddedId
    private ComplexSingleEntityId id;
    
    @ManyToOne
    @MapsId("complexEntityId")
    @JoinColumn(name = "complex_entity_id")
    private ComplexEntity complexEntity;
    
    @ManyToOne
    @MapsId("normalisedEntityId")
    @JoinColumn(name = "standardised_entity_id")
    private StandardisedEntity standardisedEntity;
    
}
