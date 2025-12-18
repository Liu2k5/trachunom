package com.liu.trachunom.service;

import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.entity.TradSimpStandard;
import com.liu.trachunom.entity.TradSimpStandardId;
import com.liu.trachunom.repository.TradSimpStandardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradSimpStandardService {
    private final TradSimpStandardRepository tradSimpStandardRepository;

    public TradSimpStandard findById(TradSimpStandardId id) {
        return tradSimpStandardRepository.findById(id).orElse(null);
    }

//    public List<TradSimpStandard> findByTraditional(CharacterX traditional) {
//        return tradSimpStandardRepository.findByTraditional(traditional);
//    }
//
//    public List<TradSimpStandard> findBySimplified(CharacterX simplified) {
//        return tradSimpStandardRepository.findBySimplified(simplified);
//    }
//
//    public List<TradSimpStandard> findByStandardised(CharacterX standardised) {
//        return tradSimpStandardRepository.findByStandardised(standardised);
//    }

    @Transactional
    public void save(TradSimpStandard tradSimpStandard) {
        tradSimpStandardRepository.save(tradSimpStandard);
    }

    @Transactional
    public void delete(TradSimpStandardId id) {
        tradSimpStandardRepository.deleteById(id);
    }

    public boolean existsById(TradSimpStandardId id) {
        return tradSimpStandardRepository.existsById(id);
    }

    public List<TradSimpStandard> findAll() {
        return tradSimpStandardRepository.findAll();
    }
}
