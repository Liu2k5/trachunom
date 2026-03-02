package com.liu.trachunom.repository;

import com.liu.trachunom.entity.pronunciation.Pronunciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PronunciationRepository extends JpaRepository<Pronunciation, Long>, JpaSpecificationExecutor<Pronunciation> {
//    @EntityGraph(attributePaths = {"quocNgu"})
//    @Override
//    List<Pronunciation> findAll();
}
