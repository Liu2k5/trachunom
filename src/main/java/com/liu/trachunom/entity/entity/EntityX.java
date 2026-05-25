package com.liu.trachunom.entity.entity;

import com.liu.trachunom.entity.meaning.Meaning;
import com.liu.trachunom.entity.pronunciation.Pronunciation;
import com.liu.trachunom.entity.structure.Structure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entity")
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

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean compound;

    private boolean attested;

    private boolean standardised;

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

    public String getExplanationsString() {
        try {
            return this.meaning.getExplanationsString();
        } catch (Exception e) {
            return "";
        }
    }

}
