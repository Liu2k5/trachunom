package com.liu.trachunom.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PronunciationChangeId {
    private Long pronunciationId;
    private Long previousPronunciationId;
}
