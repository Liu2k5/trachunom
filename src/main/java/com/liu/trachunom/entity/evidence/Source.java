package com.liu.trachunom.entity.evidence;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "source")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Source {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameQngu; // nho doi ten cot trong db
    private String nameHnom;
    private String fullNameQngu;
    private String fullNameHnom;
    
    private String fullName;

    private String authorQngu;
    private String authorHnom;
    private String writerQngu;
    private String writerHnom;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

}
