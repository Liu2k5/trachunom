package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Structure")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Structure {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unicode")
    private CharacterX character;


    @OneToMany(mappedBy = "structure", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<SubStructure> subStructures = new ArrayList<>();

}
