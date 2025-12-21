package com.liu.trachunom.repository;

import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.entity.PronunciationEvolution;
import com.liu.trachunom.entity.PronunciationEvolutionId;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PronunciationEvolutionRepository extends JpaRepository<PronunciationEvolution, PronunciationEvolutionId> {
    List<PronunciationEvolution> findByFromPronunciation(Pronunciation fromPronunciation);

    List<PronunciationEvolution> findByToPronunciation(Pronunciation fromPronunciation);
//    List<PronunciationEvolution> findByPronunciation(Pronunciation pronunciation);
//    List<PronunciationEvolution> findByPreviousPronunciation(Pronunciation previousPronunciation);
}

