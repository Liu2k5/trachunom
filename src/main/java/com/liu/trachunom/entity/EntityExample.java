package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EntityExample")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityExample {

    @EmbeddedId
    private EntityExampleId id;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    @MapsId("entityId")
    private EntityX entity;
    
    @ManyToOne
    @JoinColumn(name = "example_id")
    @MapsId("exampleId")
    private Example example;

}
