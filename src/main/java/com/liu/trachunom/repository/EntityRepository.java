package com.liu.trachunom.repository;

import com.liu.trachunom.entity.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<EntityX, Long>, JpaSpecificationExecutor<EntityX> {
    List<EntityX> findByStructure_Character(CharacterX structureCharacter);
    List<EntityX> findByCompound(boolean compound);
    EntityX findFirstByLanguageAndPronunciationAndMeaningAndStandardised(
            Language language, Pronunciation pronunciation, Meaning meaning, boolean standardised);
    List<EntityX> findByStandardised(boolean b);

    List<EntityX> findByPronunciation(Pronunciation pronunciation);

    List<EntityX> findByStructure(Structure structure);

    List<EntityX> findByPronunciation_Id(Long pronunciationId);

//    @EntityGraph(attributePaths = {"structure", "pronunciation", "meaning", "language"})
//    @Override
//    List<EntityX> findAll();
}
