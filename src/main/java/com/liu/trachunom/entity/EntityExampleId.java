package com.liu.trachunom.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class EntityExampleId implements java.io.Serializable {
    private Long entityId;
    private Long exampleId;

}
