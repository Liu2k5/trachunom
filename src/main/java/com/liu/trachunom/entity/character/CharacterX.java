package com.liu.trachunom.entity.character;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "character")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterX {
    
    @Id 
    @Column(nullable = false, unique = true)
    private Integer unicode;
    
    @ManyToOne
    @JoinColumn(name = "radical_id")
    private Radical radical;
    
    private Integer additionalStrokeNumber;

    private Integer totalStrokeNumber;

    public String getString() {
        try {
            return new String(Character.toChars(this.unicode));
        } catch (Exception e) {
            return "";
        }
    }

    public String getRadicalString() {
        try {
            return new String(Character.toChars(this.radical.getUnicode()));
        } catch  (Exception e) {
            return "";
        }
    }
    
}
