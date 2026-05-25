package com.liu.trachunom.entity.evidence;

import com.liu.trachunom.entity.entity.EntityX;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToOne
    @JoinColumn(name = "entity_id")
    private EntityX entity;

    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

}
