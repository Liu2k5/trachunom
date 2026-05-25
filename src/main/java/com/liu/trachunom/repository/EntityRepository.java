package com.liu.trachunom.repository;

import com.liu.trachunom.entity.character.CharacterX;
import com.liu.trachunom.entity.entity.EntityX;
import com.liu.trachunom.entity.entity.Language;
import com.liu.trachunom.entity.meaning.Meaning;
import com.liu.trachunom.entity.pronunciation.Pronunciation;
import com.liu.trachunom.entity.structure.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

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

//    @Override
//    @Query("SELECT e FROM EntityX e " +
//            "JOIN FETCH e.structure s " +
//            "JOIN FETCH e.pronunciation p " +
//            "JOIN FETCH e.meaning m " +
//            "JOIN FETCH m.explanations me")
//    List<EntityX> findAll();

    // EntityRepository.java
//    @Override
//    @Query("SELECT COUNT(e.id) FROM EntityX e")
//    long count();

}
