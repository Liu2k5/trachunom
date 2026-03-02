package com.liu.trachunom.repository;

import com.liu.trachunom.entity.meaning.Explanation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplanationRepository extends JpaRepository<Explanation, Long>, JpaSpecificationExecutor<Explanation> {

    Explanation findByDescription(String string);
}
