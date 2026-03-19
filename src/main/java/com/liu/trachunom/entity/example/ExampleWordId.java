package com.liu.trachunom.entity.example;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleWordId implements Serializable {
    private Long exampleId;
    private Long entityId;
    private Long position;
}
