package com.liu.trachunom.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ComplexSingleEntityId implements Serializable {
    
    private Long complexEntityId;
    
    private Long normalisedEntityId;

    private Integer entityIndex;

}
