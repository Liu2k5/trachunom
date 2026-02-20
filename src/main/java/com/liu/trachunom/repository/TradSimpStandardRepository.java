package com.liu.trachunom.repository;

import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.entity.TradSimpStandard;
import com.liu.trachunom.entity.TradSimpStandardId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradSimpStandardRepository extends JpaRepository<TradSimpStandard, TradSimpStandardId>, JpaSpecificationExecutor<TradSimpStandard> {

//    @EntityGraph(attributePaths = {"traditionalCharacter", "simplifiedCharacter"})
//    @Override
//    List<TradSimpStandard> findAll();
}
