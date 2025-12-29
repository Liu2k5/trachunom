package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "example_word")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleWord {
    @EmbeddedId
    private ExampleWordId exampleWordId;

    @MapsId("exampleId")
    @ManyToOne
    @JoinColumn(name = "example_id")
    private Example example;

    @MapsId("entityId")
    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityX entity;

}
