package com.liu.trachunom.repository;

import com.liu.trachunom.entity.character.CharacterX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.structure.Structure;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long>, JpaSpecificationExecutor<Structure> {
    List<Structure> findByCharacter(CharacterX character);

    Structure findById(long id);
}
