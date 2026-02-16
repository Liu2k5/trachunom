package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.Radical;

import java.util.List;

@Repository
public interface RadicalRepository extends JpaRepository<Radical, String>, JpaSpecificationExecutor<Radical> {
    List<Radical> findAll();

    Radical findByUnicode(int i);
}
