package com.liu.trachunom.service;

import com.liu.trachunom.entity.Explanation;
import com.liu.trachunom.repository.ExplanationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExplanationService {
    private final ExplanationRepository explanationRepository;

    public Explanation findById(Long id) {
        return explanationRepository.findById(id).orElse(null);
    }

    public List<Explanation> findAll() {
        return explanationRepository.findAll();
    }

    @Transactional
    public Explanation save(Explanation explanation) {
        return explanationRepository.save(explanation);
    }

    @Transactional
    public void delete(Long id) {
        explanationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return explanationRepository.existsById(id);
    }

    public void deleteById(Long id) {
        explanationRepository.deleteById(id);
    }

    public boolean existsByDescription(String string) {
        return explanationRepository.existsByDescription(string);
    }
}

