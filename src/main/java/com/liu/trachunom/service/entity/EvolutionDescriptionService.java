package com.liu.trachunom.service.entity;

import com.liu.trachunom.entity.entity.EvolutionDescription;
import com.liu.trachunom.repository.EvolutionDescriptionRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class EvolutionDescriptionService {
    private final EvolutionDescriptionRepository evolutionDescriptionRepository;

    @Transactional(readOnly = true)
    public EvolutionDescription findById(Long id) {
        if (id == null) {
            return null;
        }
        return evolutionDescriptionRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public EvolutionDescription findByDescription(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        EvolutionDescription byDescription = evolutionDescriptionRepository.findFirstByDescription(value);
        if (byDescription != null) {
            return byDescription;
        }
        return evolutionDescriptionRepository.findFirstByDescription(value);
    }

    public List<EvolutionDescription> findAll() {
        return evolutionDescriptionRepository.findAll();
    };
}

