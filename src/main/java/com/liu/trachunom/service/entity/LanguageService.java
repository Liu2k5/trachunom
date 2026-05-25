package com.liu.trachunom.service.entity;

import java.util.List;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.entity.Language;
import com.liu.trachunom.repository.LanguageRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class LanguageService extends ListRepositoryService<Language, Long, LanguageRepository> {
    private final LanguageRepository languageRepository;
    
    public Language findById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> new RuntimeException("Ngôn ngữ không tồn tại"));
    }

    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Transactional
    public Language save(Language language) {
        return languageRepository.save(language);
    }

    @Transactional
    public void delete(Long id) {
        languageRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return languageRepository.existsById(id);
    }

    public boolean existsByAbbreviation(String abbreviation) {
        return languageRepository.existsByAbbreviation(abbreviation);
    }

    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }
}
