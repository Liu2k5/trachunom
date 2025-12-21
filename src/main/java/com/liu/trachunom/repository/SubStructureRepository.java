package com.liu.trachunom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.entity.StructureComponent;
import com.liu.trachunom.entity.StructureComponentId;

@Repository
public interface SubStructureRepository extends JpaRepository<StructureComponent, StructureComponentId> {

    List<StructureComponent> findByStructure(Structure structure);

    List<StructureComponent> findByStructure_IdAndStructureClassification_Id(Long structureId, Long structureClassificationId);

    void deleteByStructure(Structure structure);
}
