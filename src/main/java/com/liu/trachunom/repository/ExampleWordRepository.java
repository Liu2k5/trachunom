package com.liu.trachunom.repository;

import com.liu.trachunom.entity.example.ExampleWord;
import com.liu.trachunom.entity.example.ExampleWordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleWordRepository extends JpaRepository<ExampleWord, ExampleWordId>, JpaSpecificationExecutor<ExampleWord> {
    List<ExampleWord> findByExample_IdOrderByExampleWordId_Position(Long exampleId);

    List<ExampleWord> findByEntity_Id(Long id);

    List<ExampleWord> findByEntityId(Long entityId);

//    @Override
//    @Query("SELECT ew FROM ExampleWord ew JOIN FETCH ew.example e JOIN FETCH ew.entity w")
//    List<ExampleWord> findAll();
}

