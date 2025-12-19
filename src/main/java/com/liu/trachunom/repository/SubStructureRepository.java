package com.liu.trachunom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.entity.SubStructure;
import com.liu.trachunom.entity.SubStructureId;

@Repository
public interface SubStructureRepository extends JpaRepository<SubStructure, SubStructureId> {

    List<SubStructure> findByStructure(Structure structure);

    List<SubStructure> findByStructure_IdAndStructureClassification_Id(Long structureId, Long structureClassificationId);

    void deleteByStructure(Structure structure);
}
