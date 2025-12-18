package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.EntityExample;
import com.liu.trachunom.entity.EntityExampleId;

@Repository
public interface EntityExampleRepository extends JpaRepository<EntityExample, EntityExampleId> {
}
