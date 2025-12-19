package com.liu.trachunom.repository;

import com.liu.trachunom.entity.PronunciationClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PronunciationClassificationRepository extends JpaRepository<PronunciationClassification,Long> {

    PronunciationClassification findByDescription(String string);
}
