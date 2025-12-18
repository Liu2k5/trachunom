package com.liu.trachunom.service;

import org.springframework.stereotype.Service;

import com.liu.trachunom.dto.MeaningDto;
import com.liu.trachunom.entity.Explanation;
import com.liu.trachunom.entity.Meaning;
import com.liu.trachunom.repository.MeaningRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeaningService {

    private final MeaningRepository meaningRepository;
    private final ExplanationService explanationService;

    public Meaning findById(Long id) {
        return meaningRepository.findById(id).orElse(null);
    }

    public List<Meaning> findAll() {
        return meaningRepository.findAll();
    }

    public MeaningDto toDto(Meaning meaning) {
        if (meaning == null) {
            return null;
        }
        MeaningDto dto = new MeaningDto();
        dto.setId(meaning.getId());
        // Combine all explanation contents into description
        if (meaning.getExplanations() != null && !meaning.getExplanations().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Explanation exp : meaning.getExplanations()) {
                if (sb.length() > 0) sb.append("; ");
                sb.append(exp.getDescription());
            }
            dto.setDescription(sb.toString());
        }
        return dto;
    }

    @Transactional
    public Meaning save(Meaning meaning) {
        return meaningRepository.save(meaning);
    }

    @Transactional
    public Meaning save(MeaningDto meaningDto) {
        Meaning meaning;

        if (meaningDto.getId() != null && meaningRepository.existsById(meaningDto.getId())) {
            meaning = meaningRepository.findById(meaningDto.getId()).orElse(new Meaning());
        } else {
            meaning = new Meaning();
        }

        // Create or update explanation if description is provided
        if (meaningDto.getDescription() != null && !meaningDto.getDescription().trim().isEmpty()) {
            Explanation explanation = new Explanation();
            explanation.setDescription(meaningDto.getDescription().trim());
            explanation = explanationService.save(explanation);

            if (meaning.getExplanations() == null) {
                meaning.setExplanations(new ArrayList<>());
            }
            meaning.getExplanations().clear();
            meaning.getExplanations().add(explanation);
        }

        return meaningRepository.save(meaning);
    }

    @Transactional
    public void delete(Long id) {
        meaningRepository.deleteById(id);
    }

    public boolean existsById(Long meaningId) {
        return meaningRepository.existsById(meaningId);
    }
}
