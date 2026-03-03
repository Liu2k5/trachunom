package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.meaning.Meaning;

import java.util.List;

@Repository
public interface MeaningRepository extends JpaRepository<Meaning, Long>, JpaSpecificationExecutor<Meaning> {

//    @Override
//    @Query("SELECT m FROM Meaning m JOIN FETCH m.explanations")
//    List<Meaning> findAll();
}
