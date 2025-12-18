package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.ComplexSingleEntity;
import com.liu.trachunom.entity.ComplexSingleEntityId;

@Repository
public interface ComplexSingleEntityRepository extends JpaRepository<ComplexSingleEntity, ComplexSingleEntityId> {
}
