package com.liu.trachunom.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "StandardisedEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardisedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id")
    private EntityX entity;

//    public String getQuocNguDescription() {
//        if (entity != null && entity.getPronunciation() != null && entity.getPronunciation().getQuocNgu() != null) {
//            return entity.getPronunciation().getQuocNgu().getDescription();
//        }
//        return null;
//    }
}
