package com.liu.trachunom.repository;

import com.liu.trachunom.entity.CharacterX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.EntityX;

import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<EntityX, Long> {
    List<EntityX> findByStructure_Character(CharacterX structureCharacter);
    List<EntityX> findByCompound(boolean compound);
}
