package com.liu.trachunom.repository;

import com.liu.trachunom.entity.entity.EntityComposition;
import com.liu.trachunom.entity.entity.EntityCompositionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityCompositionRepository extends JpaRepository<EntityComposition, EntityCompositionId> {
//    @Query("SELECT ec FROM EntityComposition ec JOIN FETCH ec.childEntity ce WHERE ec.id.parentEntityId = :id")
    List<EntityComposition> findByParentEntityId(@Param("id") Long parentEntityId);

//    @Override
//    @Query("SELECT ec FROM EntityComposition ec JOIN FETCH ec.parentEntity pe JOIN FETCH ec.childEntity ce")
    List<EntityComposition> findAll();

    List<EntityComposition> findByChildEntityId(Long id);

//    @Override
//    @Query("SELECT COUNT(ec.id.parentEntityId) FROM EntityComposition ec")
//    long count();

}

