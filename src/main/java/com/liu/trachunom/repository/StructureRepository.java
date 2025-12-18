package com.liu.trachunom.repository;

import com.liu.trachunom.entity.CharacterX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.liu.trachunom.entity.Structure;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {
    List<Structure> findByCharacter(CharacterX character);

    @Query("select distinct s from Structure s left join fetch s.subStructures")
    List<Structure> findAllWithSubStructures();
}
