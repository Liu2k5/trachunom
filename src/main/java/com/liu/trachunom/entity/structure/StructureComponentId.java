package com.liu.trachunom.entity.structure;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StructureComponentId implements Serializable {
    private Long structureId;
    private Long structureComponentId;
    private Long classificationId;
}
