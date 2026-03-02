package com.liu.trachunom.entity.pronunciation;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pronunciation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pronunciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quoc_ngu_id")
    private QuocNgu quocNgu;

    public String getString() {
        try {
            return this.quocNgu.getDescription();
        } catch (Exception e) {
            return "";
        }
    }

}
