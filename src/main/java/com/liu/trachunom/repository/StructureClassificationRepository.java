package com.liu.trachunom.repository;

import com.liu.trachunom.entity.structure.StructureClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureClassificationRepository extends JpaRepository<StructureClassification, Long>, JpaSpecificationExecutor<StructureClassification> {
    StructureClassification findByDescription(String description);
}
