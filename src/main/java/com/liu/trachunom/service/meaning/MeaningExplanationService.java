package com.liu.trachunom.service.meaning;

import com.liu.trachunom.entity.meaning.Meaning;
import com.liu.trachunom.entity.meaning.MeaningExplanation;
import com.liu.trachunom.entity.meaning.MeaningExplanationId;
import com.liu.trachunom.repository.MeaningExplanationRepository;
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
public class MeaningExplanationService extends ListRepositoryService<MeaningExplanation, MeaningExplanationId, MeaningExplanationRepository> {
    private final MeaningExplanationRepository meaningExplanationRepository;

    public MeaningExplanation findById(MeaningExplanationId id) {
        return meaningExplanationRepository.findById(id).orElse(null);
    }

    public List<MeaningExplanation> findByMeaning(Meaning meaning) {
        return meaningExplanationRepository.findByMeaning(meaning);
    }

    @Transactional
    public void save(MeaningExplanation meaningExplanation) {
        meaningExplanationRepository.save(meaningExplanation);
    }

    @Transactional
    public void delete(MeaningExplanationId id) {
        meaningExplanationRepository.deleteById(id);
    }

    public boolean existsById(MeaningExplanationId id) {
        return meaningExplanationRepository.existsById(id);
    }

    public List<MeaningExplanation> findAll() {
        return meaningExplanationRepository.findAll();
    }

    public void deleteById(MeaningExplanationId id) {
        meaningExplanationRepository.deleteById(id);
    }
}
