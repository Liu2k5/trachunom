package com.liu.trachunom.repository;

import com.liu.trachunom.entity.entity.EntityEvolution;
import com.liu.trachunom.entity.entity.EntityEvolutionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityEvolutionRepository extends JpaRepository<EntityEvolution, EntityEvolutionId> {
    List<EntityEvolution> findByFromEntityId(Long fromEntityId);

    List<EntityEvolution> findByToEntityId(Long toEntityId);

    void deleteById_FromEntityIdAndId_ToEntityIdAndId_DescriptionId(Long fromEntityId, Long toEntityId, Long descriptionId);
}

