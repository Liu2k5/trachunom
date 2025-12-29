package com.liu.trachunom.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "meaning")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Meaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "Meaning_Explanation",
        joinColumns = @JoinColumn(name = "meaning_id"),
        inverseJoinColumns = @JoinColumn(name = "explanation_id")
    )
    private List<Explanation> explanations;

    public String getExplanationsString() {
        StringBuilder sb = new StringBuilder();
        for (Explanation explanation : explanations) {
            if (!sb.isEmpty()) {
                sb.append("; \n");
            }
            sb.append(explanation.getDescription());
        }
        return sb.toString();
    }
}
