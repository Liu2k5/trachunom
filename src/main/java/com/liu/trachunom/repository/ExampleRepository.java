package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Example;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}
