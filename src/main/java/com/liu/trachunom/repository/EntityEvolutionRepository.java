package com.liu.trachunom.repository;

import com.liu.trachunom.entity.EntityEvolution;
import com.liu.trachunom.entity.EntityEvolutionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityEvolutionRepository extends JpaRepository<EntityEvolution, EntityEvolutionId> {
    @Query("SELECT ee FROM EntityEvolution ee " +
            "LEFT JOIN FETCH ee.fromEntity " +
            "LEFT JOIN FETCH ee.toEntity")
    List<EntityEvolution> findAllWithEntities();

    List<EntityEvolution> findByFromEntityId(Long fromEntityId);

    List<EntityEvolution> findByToEntityId(Long toEntityId);
}

