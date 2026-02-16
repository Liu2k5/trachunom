package com.liu.trachunom.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
@Data
public class StructureComponentId implements Serializable {
    private Long structureId;
    private Long structureComponentId;
    private Long classificationId;
}
