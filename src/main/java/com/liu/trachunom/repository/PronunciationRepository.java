package com.liu.trachunom.repository;

import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.entity.QuocNgu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PronunciationRepository extends JpaRepository<Pronunciation, Long>, JpaSpecificationExecutor<Pronunciation> {

}
