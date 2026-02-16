package com.liu.trachunom.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MeaningExplanationId {
    private Long meaningId;
    private Long explanationId;
}
