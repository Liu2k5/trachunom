package com.liu.trachunom.entity.pronunciation;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quoc_ngu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuocNgu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

}

