package com.liu.trachunom.repository;

import com.liu.trachunom.entity.EntityX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.StandardisedEntity;

@Repository
public interface StandardisedRepository extends JpaRepository<StandardisedEntity, Long> {
    boolean existsByEntity_Id(Long entityId);
}
