package com.liu.trachunom.repository;

import com.liu.trachunom.entity.entity.EvolutionDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvolutionDescriptionRepository extends JpaRepository<EvolutionDescription, Long> {
    EvolutionDescription findFirstByDescription(String description);
}

