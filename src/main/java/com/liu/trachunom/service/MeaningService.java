package com.liu.trachunom.service;

import com.liu.trachunom.entity.Meaning;
import com.liu.trachunom.repository.MeaningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeaningService {
    private final MeaningRepository meaningRepository;

    public List<Meaning> findAll() {
        return meaningRepository.findAll();
    }

    public Optional<Meaning> findById(Long id) {
        return meaningRepository.findById(id);
    }

    @Transactional
    public Meaning save(Meaning meaning) {
        return meaningRepository.save(meaning);
    }

    @Transactional
    public void deleteById(Long id) {
        meaningRepository.deleteById(id);
    }
}

