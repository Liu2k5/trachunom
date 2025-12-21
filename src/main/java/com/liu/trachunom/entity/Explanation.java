package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Explanation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Explanation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    @Nationalized
    private String description;
}
