package com.liu.trachunom.repository;

import com.liu.trachunom.entity.Example;
import com.liu.trachunom.entity.ExampleWord;
import com.liu.trachunom.entity.ExampleWordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleWordRepository extends JpaRepository<ExampleWord, ExampleWordId>, JpaSpecificationExecutor<ExampleWord> {
    List<ExampleWord> findByExample_IdOrderByExampleWordId_Position(Long exampleId);

    List<ExampleWord> findByEntity_Id(Long id);
}

