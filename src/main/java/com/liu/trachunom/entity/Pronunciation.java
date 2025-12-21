package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pronunciation")
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

}
