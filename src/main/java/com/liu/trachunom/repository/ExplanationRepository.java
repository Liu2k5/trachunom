package com.liu.trachunom.repository;

import com.liu.trachunom.entity.Explanation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplanationRepository extends JpaRepository<Explanation, Long> {
    boolean existsByDescription(String string);
}

