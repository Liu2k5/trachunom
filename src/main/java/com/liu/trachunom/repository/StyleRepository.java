package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    boolean existsByDescription(String description);
}
