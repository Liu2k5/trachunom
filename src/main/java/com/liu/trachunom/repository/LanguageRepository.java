package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByAbbreviation(String abbreviation);
}
