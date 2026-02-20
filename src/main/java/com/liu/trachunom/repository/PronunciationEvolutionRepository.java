package com.liu.trachunom.repository;

import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.entity.PronunciationEvolution;
import com.liu.trachunom.entity.PronunciationEvolutionId;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PronunciationEvolutionRepository extends JpaRepository<PronunciationEvolution, PronunciationEvolutionId>, JpaSpecificationExecutor<PronunciationEvolution> {
    List<PronunciationEvolution> findByFromPronunciation(Pronunciation fromPronunciation);

    List<PronunciationEvolution> findByToPronunciation(Pronunciation fromPronunciation);

//    @EntityGraph(attributePaths = {"fromPronunciation", "toPronunciation"})
//    @Override
//    List<PronunciationEvolution> findAll();

    List<PronunciationEvolution> findByFromPronunciation_Id(Long fromPronunciationId);

    List<PronunciationEvolution> findByToPronunciation_Id(Long toPronunciationId);
}

