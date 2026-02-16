package com.liu.trachunom.service;

import com.liu.trachunom.entity.Explanation;
import com.liu.trachunom.entity.Meaning;
import com.liu.trachunom.repository.ExplanationRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class ExplanationService extends ListRepositoryService<Explanation, Long, ExplanationRepository> {

    private final ExplanationRepository explanationRepository;

    public Explanation findById(Long id) {
        return explanationRepository.findById(id).orElse(null);
    }

    public List<Explanation> findAll() {
        return explanationRepository.findAll();
    }

    public List<Explanation> findByMeaning(Meaning meaning) {
        // Trả về danh sách explanations từ meaning (quan hệ nhiều-nhiều)
        return meaning.getExplanations();
    }

    @Transactional
    public Explanation save(Explanation explanation) {
        return explanationRepository.save(explanation);
    }

    public boolean existsById(Long meaningId) {
        return explanationRepository.existsById(meaningId);
    }

    public boolean existsByDescription(String string, Explanation explanation) {
        Explanation foundExplanation = explanationRepository.findByDescription(string);
        if (foundExplanation == null) {
            return false;
        }
        return !foundExplanation.getDescription().trim().equalsIgnoreCase(explanation.getDescription().trim());
    }

    @Transactional
    public void deleteById(Long id) {
        explanationRepository.deleteById(id);
    }
}
