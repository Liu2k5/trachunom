package com.liu.trachunom.entity.pronunciation;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PronunciationEvolutionId implements Serializable {
    private Long fromPronunciationId;
    private Long toPronunciationId;

    public String getString() {
        return fromPronunciationId.toString() + "-" + toPronunciationId.toString();
    }
}
