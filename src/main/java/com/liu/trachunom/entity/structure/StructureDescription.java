package com.liu.trachunom.entity.structure;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "structure_description")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StructureDescription {

    @Id
    @Column(name = "structure_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @ManyToOne
    @JoinColumn(name = "description_structure_id")
    private Structure descriptionStructure;

}

