package com.liu.trachunom.service.pronunciation;

import com.liu.trachunom.entity.pronunciation.Pronunciation;
import com.liu.trachunom.repository.PronunciationRepository;
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
public class PronunciationService extends ListRepositoryService<Pronunciation, Long, PronunciationRepository> {
    private final PronunciationRepository pronunciationRepository;
    private final QuocNguService quocNguService;
    private final PronunciationEvolutionService pronunciationEvolutionService;

    public Pronunciation findById(Long pronunciationId) {
        return pronunciationRepository.findById(pronunciationId).orElse(null);
    }

    public List<Pronunciation> findAll() {
        return pronunciationRepository.findAll();
    }

    @Transactional
    public Pronunciation save(Pronunciation pronunciation) {
        return pronunciationRepository.save(pronunciation);
    }

    @Transactional
    public void delete(Long id) {
        pronunciationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return pronunciationRepository.existsById(id);
    }

    public void deleteById(Long id) {
        pronunciationRepository.deleteById(id);
    }

}
