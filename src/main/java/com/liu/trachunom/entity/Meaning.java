package com.liu.trachunom.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "Meaning_Explanation",
        joinColumns = @JoinColumn(name = "meaning_id"),
        inverseJoinColumns = @JoinColumn(name = "explanation_id")
    )
    private List<Explanation> explanations;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Explanation explanation : explanations) {
            sb.append(explanation.getDescription()).append("; ");
        }
        return sb.toString();
    }

}
