package com.liu.trachunom.repository;

import com.liu.trachunom.entity.entity.EntityComposition;
import com.liu.trachunom.entity.entity.EntityCompositionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityCompositionRepository extends JpaRepository<EntityComposition, EntityCompositionId> {
    List<EntityComposition> findByIdParentEntityId(Long parentEntityId);
    void deleteByIdParentEntityId(Long parentEntityId);
}

