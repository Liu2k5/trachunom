package com.liu.trachunom.entity;

import jakarta.persistence.Entity;
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

@Entity
@Table(name = "picture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Picture {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "unicode")
    private CharacterX character;
    
    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;
    
    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;
    
    private String link;
    
}
