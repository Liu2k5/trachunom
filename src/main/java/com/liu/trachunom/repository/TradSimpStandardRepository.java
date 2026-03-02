package com.liu.trachunom.repository;

import com.liu.trachunom.entity.character.TradSimpStandard;
import com.liu.trachunom.entity.character.TradSimpStandardId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TradSimpStandardRepository extends JpaRepository<TradSimpStandard, TradSimpStandardId>, JpaSpecificationExecutor<TradSimpStandard> {

//    @EntityGraph(attributePaths = {"traditionalCharacter", "simplifiedCharacter"})
//    @Override
//    List<TradSimpStandard> findAll();
}
