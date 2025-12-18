package com.liu.trachunom.repository;

import java.util.List;
import java.util.Optional;

import com.liu.trachunom.entity.Classification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.entity.SubStructure;
import com.liu.trachunom.entity.SubStructureId;

@Repository
public interface SubStructureRepository extends JpaRepository<SubStructure, SubStructureId> {

    List<SubStructure> findByStructure(Structure structure);

    List<SubStructure> findByStructure_IdAndClassification_Id(Long structureId, Long classificationId);

    void deleteByStructure(Structure structure);
}
