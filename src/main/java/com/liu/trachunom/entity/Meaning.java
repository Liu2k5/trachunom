package com.liu.trachunom.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Meaning")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "Meaning_Explanation",
        joinColumns = @JoinColumn(name = "meaning_id"),
        inverseJoinColumns = @JoinColumn(name = "explanation_id")
    )
    private List<Explanation> explanations;
}
