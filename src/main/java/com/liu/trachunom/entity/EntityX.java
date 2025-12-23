package com.liu.trachunom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@Entity
@Table(name = "Entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityX {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pronunciation_id")
    private Pronunciation pronunciation;

    @ManyToOne
    @JoinColumn(name = "meaning_id")
    private Meaning meaning;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Nationalized
    private String description;

    private boolean compound;

    private boolean attested;

    public String getCharacterString() {
        try {
            return this.structure.getCharacterString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getPronunciationString() {
        try {
            return this.pronunciation.getString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getStructureId() {
        try {
            return this.structure.getId().toString();
        } catch (Exception e) {
            return "";
        }
    }

}
