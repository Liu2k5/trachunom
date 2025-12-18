package com.liu.trachunom.repository;

import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.entity.TradSimpStandard;
import com.liu.trachunom.entity.TradSimpStandardId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradSimpStandardRepository extends JpaRepository<TradSimpStandard, TradSimpStandardId> {
//    List<TradSimpStandard> findByTraditional(CharacterX traditional);
//    List<TradSimpStandard> findBySimplified(CharacterX simplified);
//    List<TradSimpStandard> findByStandardised(CharacterX standardised);
}
