package com.liu.trachunom.entity;

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
@Table(name = "Radical")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Radical {
    
    @Id
    @Column(nullable = false, unique = true)
    private String id;
    
    private Integer radicalUnicode;
    
    private Integer unicode;
    
    private Integer strokeNumber;

    public String getString() {
        if (this.unicode == null || this.unicode <= 0) {
            return "";
        }
        return new String(Character.toChars(this.unicode));
    }
}
