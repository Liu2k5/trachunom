package com.liu.trachunom.entity.structure;

import com.liu.trachunom.entity.character.CharacterX;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "structure_type_id")
    private StructureType structureType;

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
