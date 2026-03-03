package com.liu.trachunom.repository;

import java.util.List;

import com.liu.trachunom.entity.structure.StructureComponentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.structure.Structure;
import com.liu.trachunom.entity.structure.StructureComponent;

@Repository
public interface StructureComponentRepository extends JpaRepository<StructureComponent, StructureComponentId>, JpaSpecificationExecutor<StructureComponent> {

    List<StructureComponent> findByStructure(Structure structure);

    List<StructureComponent> findByStructureId(Long structureId);

    List<StructureComponent> findByStructure_IdAndStructureClassification_Id(Long structureId, Long structureClassificationId);

    void deleteByStructure(Structure structure);

    List<StructureComponent> findByStructureComponent(Structure structure);

//    @Override
//    @Query("SELECT sc FROM StructureComponent sc " +
//            "JOIN FETCH sc.structure s " +
//            "JOIN FETCH sc.structureComponent scc " +
//            "JOIN FETCH sc.structureClassification sca")
//    List<StructureComponent> findAll();
}
