package com.liu.trachunom.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@jakarta.persistence.Entity
@Table(name = "ComplexEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplexEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "meaning_id")
    private Meaning meaning;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Nationalized
    private String description;

}
