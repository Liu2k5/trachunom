package com.liu.trachunom.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "QuocNgu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuocNgu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    @Nationalized
    private String description;

}

