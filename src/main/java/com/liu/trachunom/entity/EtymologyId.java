package com.liu.trachunom.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtymologyId {
    private Long meaningId;
    private Long originId;
}
