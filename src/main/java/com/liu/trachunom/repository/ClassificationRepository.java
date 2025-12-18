package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Classification;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findByDescription(String description);
}
