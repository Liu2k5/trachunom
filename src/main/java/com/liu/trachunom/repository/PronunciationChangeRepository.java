package com.liu.trachunom.repository;

import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.entity.PronunciationChange;
import com.liu.trachunom.entity.PronunciationChangeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PronunciationChangeRepository extends JpaRepository<PronunciationChange, PronunciationChangeId> {
    List<PronunciationChange> findByPronunciation(Pronunciation pronunciation);
    List<PronunciationChange> findByPreviousPronunciation(Pronunciation previousPronunciation);
}

