package com.liu.trachunom.service;

import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.entity.PronunciationEvolution;
import com.liu.trachunom.entity.PronunciationEvolutionId;
import com.liu.trachunom.repository.PronunciationEvolutionRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class PronunciationEvolutionService extends ListRepositoryService<PronunciationEvolution, PronunciationEvolutionId, PronunciationEvolutionRepository> {
    private final PronunciationEvolutionRepository pronunciationEvolutionRepository;

    public PronunciationEvolution findById(PronunciationEvolutionId id) {
        return pronunciationEvolutionRepository.findById(id).orElse(null);
    }

    public List<PronunciationEvolution> findByFromPronunciation(Pronunciation fromPronunciation) {
        return pronunciationEvolutionRepository.findByFromPronunciation(fromPronunciation);
    }

    @Transactional
    public void save(PronunciationEvolution pronunciationEvolution) {
        pronunciationEvolutionRepository.save(pronunciationEvolution);
    }

    @Transactional
    public void delete(PronunciationEvolutionId id) {
        pronunciationEvolutionRepository.deleteById(id);
    }

    public boolean existsById(PronunciationEvolutionId id) {
        return pronunciationEvolutionRepository.existsById(id);
    }

    public List<PronunciationEvolution> findAll() {
        return pronunciationEvolutionRepository.findAll();
    }

    public void deleteById(PronunciationEvolutionId id) {
        pronunciationEvolutionRepository.deleteById(id);
    }


    public List<PronunciationEvolution> findByToPronunciation(Pronunciation fromPronunciation) {
        return pronunciationEvolutionRepository.findByToPronunciation(fromPronunciation);
    }

    public List<PronunciationEvolution> findByFromPronunciationId(Long fromPronunciationId) {
        return pronunciationEvolutionRepository.findByFromPronunciation_Id(fromPronunciationId);
    }

    public List<PronunciationEvolution> findByToPronunciationId(Long toPronunciationId) {
        return pronunciationEvolutionRepository.findByToPronunciation_Id(toPronunciationId);
    }
}

