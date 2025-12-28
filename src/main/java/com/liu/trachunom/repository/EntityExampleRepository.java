package com.liu.trachunom.repository;

import com.liu.trachunom.entity.EntityExample;
import com.liu.trachunom.entity.EntityExampleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityExampleRepository extends JpaRepository<EntityExample, EntityExampleId> {
    @Query("SELECT ee FROM EntityExample ee WHERE ee.entity.id = :entityId")
    List<EntityExample> findByEntityId(@Param("entityId") Long entityId);

    @Query("SELECT ee FROM EntityExample ee WHERE ee.example.id = :exampleId")
    List<EntityExample> findByExampleId(@Param("exampleId") Long exampleId);
}
