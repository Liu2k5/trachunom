package com.liu.trachunom.service.meaning;

import com.liu.trachunom.entity.entity.EntityX;
import com.liu.trachunom.entity.meaning.Meaning;
import com.liu.trachunom.entity.pronunciation.Pronunciation;
import com.liu.trachunom.repository.EntityRepository;
import com.liu.trachunom.repository.MeaningRepository;
import com.liu.trachunom.repository.PronunciationRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class MeaningService extends ListRepositoryService<Meaning, Long, MeaningRepository> {
    private final MeaningRepository meaningRepository;
    private final EntityRepository  entityRepository;
    private final PronunciationRepository pronunciationRepository;

    public List<Meaning> findAll() {
        return meaningRepository.findAll();
    }

    public Meaning findById(Long id) {
        if (id == null) return null;
        return meaningRepository.findById(id).orElse(null);
    }

    @Transactional
    public Meaning save(Meaning meaning) {
        return meaningRepository.save(meaning);
    }

    @Transactional
    public void deleteById(Long id) {
        meaningRepository.deleteById(id);
    }

    public List<Meaning> findAllWithPronunciation(Pronunciation pronunciation) {
        List<Meaning> meanings = entityRepository.findByPronunciation(pronunciation)
                .stream()
                .sorted((o1, o2) -> {
                    if (o1.isStandardised() && !o2.isStandardised()) {
                        return -1;
                    }
                    if (!o1.isStandardised() && o2.isStandardised()) {
                        return 1;
                    }
                    if (o1.isAttested() && !o2.isAttested()) {
                        return -1;
                    }
                    if (!o1.isAttested() && o2.isAttested()) {
                        return 1;
                    }
                    return o1.getId().compareTo(o2.getId());
                })
                .map(EntityX::getMeaning)
                .distinct()
                .collect(Collectors.toList());
        meanings.addAll(meaningRepository.findAll());
        return meanings.stream()
                .distinct()
                .toList();
    }

    public List<Meaning> findByPronunciationId(Long id) {
        Pronunciation pronunciation = pronunciationRepository.findById(id).orElse(null);
        if (pronunciation == null) return List.of();

        List<EntityX> relatedEntities = entityRepository.findByPronunciation(pronunciation);
        return relatedEntities.stream()
                .map(EntityX::getMeaning)
                .distinct()
                .toList();
    }
}

