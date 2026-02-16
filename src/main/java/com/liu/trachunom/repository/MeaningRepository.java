package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Meaning;

@Repository
public interface MeaningRepository extends JpaRepository<Meaning, Long>, JpaSpecificationExecutor<Meaning> {

}
