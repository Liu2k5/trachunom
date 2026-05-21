package com.liu.trachunom.repository;

import com.liu.trachunom.entity.structure.StructureDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureDescriptionRepository extends JpaRepository<StructureDescription, Long>, JpaSpecificationExecutor<StructureDescription> {
    StructureDescription findByStructureId(Long structureId);
}
