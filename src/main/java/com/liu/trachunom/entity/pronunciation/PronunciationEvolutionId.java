package com.liu.trachunom.entity.pronunciation;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PronunciationEvolutionId implements Serializable {
    private Long fromPronunciationId;
    private Long toPronunciationId;

    public String getString() {
        return fromPronunciationId.toString() + "-" + toPronunciationId.toString();
    }
}
