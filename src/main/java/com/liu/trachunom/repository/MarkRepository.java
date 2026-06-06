package com.liu.trachunom.repository;
import com.liu.trachunom.entity.evidence.MarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.liu.trachunom.entity.evidence.Mark;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, MarkId>, JpaSpecificationExecutor<Mark> {
    List<Mark> findByImage_Id(Long imageId);

    List<Mark> findByEntity_Id(Long entityId);
}
