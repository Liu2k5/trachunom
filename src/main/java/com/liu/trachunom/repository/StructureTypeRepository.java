package com.liu.trachunom.repository;

import com.liu.trachunom.entity.structure.StructureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureTypeRepository extends JpaRepository<StructureType, Long> {
}
