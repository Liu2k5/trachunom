package com.liu.trachunom.service;

import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.entity.PronunciationChange;
import com.liu.trachunom.repository.PronunciationRepository;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PronunciationService {
    private final PronunciationRepository pronunciationRepository;
    private final QuocNguService quocNguService;
    private final VisualTool visualTool;

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

    public VerticalLayout drawPronunciation(Pronunciation pronunciation) {
        List<PronunciationChange> pronunciationChanges = pronunciation.getPronunciationChanges();
        return visualTool.drawPronunciation(pronunciationChanges);
    }

    public VerticalLayout drawPronunciation(List<PronunciationChange> pronunciationChanges) {
        return visualTool.drawPronunciation(pronunciationChanges);
    }
}
