package com.liu.trachunom.repository;

import com.liu.trachunom.entity.Meaning;
import com.liu.trachunom.entity.MeaningExplanation;
import com.liu.trachunom.entity.MeaningExplanationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeaningExplanationRepository extends JpaRepository<MeaningExplanation, MeaningExplanationId>, JpaSpecificationExecutor<MeaningExplanation> {
    List<MeaningExplanation> findByMeaning(Meaning meaning);
}
