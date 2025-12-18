package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Source;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
    boolean existsByName(String string);
}
