package com.liu.trachunom.service;

import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.entity.PronunciationChange;
import com.liu.trachunom.entity.PronunciationChangeId;
import com.liu.trachunom.repository.PronunciationChangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PronunciationChangeService {
    private final PronunciationChangeRepository pronunciationChangeRepository;

    public PronunciationChange findById(PronunciationChangeId id) {
        return pronunciationChangeRepository.findById(id).orElse(null);
    }

    public List<PronunciationChange> findByPronunciation(Pronunciation pronunciation) {
        return pronunciationChangeRepository.findByPronunciation(pronunciation);
    }

    public List<PronunciationChange> findByPreviousPronunciation(Pronunciation previousPronunciation) {
        return pronunciationChangeRepository.findByPreviousPronunciation(previousPronunciation);
    }

    @Transactional
    public void save(PronunciationChange pronunciationChange) {
        pronunciationChangeRepository.save(pronunciationChange);
    }

    @Transactional
    public void delete(PronunciationChangeId id) {
        pronunciationChangeRepository.deleteById(id);
    }

    public boolean existsById(PronunciationChangeId id) {
        return pronunciationChangeRepository.existsById(id);
    }

    public List<PronunciationChange> findAll() {
        return pronunciationChangeRepository.findAll();
    }

    public void deleteById(PronunciationChangeId id) {
        pronunciationChangeRepository.deleteById(id);
    }
}

