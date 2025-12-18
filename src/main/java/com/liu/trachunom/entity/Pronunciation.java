package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Pronunciation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pronunciation {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quoc_ngu_id")
    private QuocNgu quocNgu;
}
