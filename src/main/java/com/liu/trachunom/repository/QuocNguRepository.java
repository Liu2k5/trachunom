package com.liu.trachunom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.liu.trachunom.entity.pronunciation.QuocNgu;

import java.util.List;

@Repository
public interface QuocNguRepository extends JpaRepository<QuocNgu, Long>, JpaSpecificationExecutor<QuocNgu> {
    List<QuocNgu> findByDescription(String description);

    boolean existsByDescription(String description);
}
