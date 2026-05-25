package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {
    boolean existsByAbbreviation(String abbreviation);
}
