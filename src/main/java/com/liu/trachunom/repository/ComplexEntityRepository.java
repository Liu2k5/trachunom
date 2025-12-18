package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.ComplexEntity;

@Repository
public interface ComplexEntityRepository extends JpaRepository<ComplexEntity, Long> {
}
