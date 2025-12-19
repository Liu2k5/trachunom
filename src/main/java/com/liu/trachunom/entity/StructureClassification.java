package com.liu.trachunom.entity;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "StructureClassification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StructureClassification {
    
    @Id
    private Long id;
    
    @Column(columnDefinition = "NVARCHAR(MAX)")
    @Nationalized
    private String description;
}
