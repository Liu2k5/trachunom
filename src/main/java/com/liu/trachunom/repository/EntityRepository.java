package com.liu.trachunom.repository;

import com.liu.trachunom.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<EntityX, Long> {
    List<EntityX> findByStructure_Character(CharacterX structureCharacter);
    List<EntityX> findByCompound(boolean compound);
    EntityX findFirstByLanguageAndPronunciationAndMeaningAndStandardised(
            Language language, Pronunciation pronunciation, Meaning meaning, boolean standardised);
    List<EntityX> findByStandardised(boolean b);

    List<EntityX> findByPronunciation(Pronunciation pronunciation);
}
