package com.liu.trachunom.entity.character;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trad_simp_standard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradSimpStandard {
    @EmbeddedId
    private TradSimpStandardId id;

    @MapsId("traditionalUnicode")
    @ManyToOne
    @JoinColumn(name = "traditional_unicode")
    private CharacterX traditionalCharacter;

    @MapsId("simplifiedUnicode")
    @ManyToOne
    @JoinColumn(name = "simplified_unicode")
    private CharacterX simplifiedCharacter;

}
