package com.liu.trachunom.repository;

import com.liu.trachunom.entity.StructureClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<StructureClassification, Long> {
    StructureClassification findByDescription(String description);
}
