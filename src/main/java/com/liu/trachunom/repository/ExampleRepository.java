package com.liu.trachunom.repository;

import com.liu.trachunom.entity.example.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long>, JpaSpecificationExecutor<Example> {
//    @EntityGraph(attributePaths = {"source"})
//    @Override
//    List<Example> findAll();
}
