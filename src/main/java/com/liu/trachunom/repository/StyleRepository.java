package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.evidence.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long>, JpaSpecificationExecutor<Style> {
    boolean existsByDescription(String description);
}
