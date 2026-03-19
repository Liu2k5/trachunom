package com.liu.trachunom.entity.meaning;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeaningExplanationId {
    private Long meaningId;
    private Long explanationId;
}
