package com.liu.trachunom.repository;

import java.util.List;

import com.liu.trachunom.entity.StructureComponentId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.entity.StructureComponent;

@Repository
public interface StructureComponentRepository extends JpaRepository<StructureComponent, StructureComponentId>, JpaSpecificationExecutor<StructureComponent> {

    List<StructureComponent> findByStructure(Structure structure);

    List<StructureComponent> findByStructureId(Long structureId);

    List<StructureComponent> findByStructure_IdAndStructureClassification_Id(Long structureId, Long structureClassificationId);

    void deleteByStructure(Structure structure);

//    @EntityGraph(attributePaths = {"structure", "structureComponent"})
//    @Override
//    List<StructureComponent> findAll();
}
