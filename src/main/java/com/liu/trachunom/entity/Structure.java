package com.liu.trachunom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "structure")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Structure {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unicode")
    private CharacterX character;

    public String getCharacterString() {
        try {
            if (this.character != null) {
                return this.character.getString();
            }
            return "[Cấu tạo #" + this.id + "]";
        } catch (Exception e) {
            return "[Cấu tạo #" + this.id + "]";
        }
    }


}
