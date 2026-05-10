package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.MeaningExplanationDto;
import com.liu.trachunom.entity.meaning.Meaning;
import com.liu.trachunom.entity.meaning.MeaningExplanation;
import com.liu.trachunom.entity.meaning.MeaningExplanationId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.meaning.MeaningExplanationService;
import com.liu.trachunom.service.meaning.MeaningService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class MeaningExplanationEndpoint {
    private final MeaningExplanationService meaningExplanationService;
    private final MeaningService meaningService;
    private final EntityMapper entityMapper;

    public List<MeaningExplanationDto> findByMeaningId(Long meaningId) {
        Meaning tempMeaning = meaningService.findById(meaningId);
        if (tempMeaning == null) {
            return Collections.emptyList();
        }
        return meaningExplanationService.findByMeaning(tempMeaning).stream()
                        .map(entityMapper::toMeaningExplanationDto)
                        .collect(Collectors.toList());
    }

    public MeaningExplanationDto save(MeaningExplanationDto meaningExplanationDto) {
        MeaningExplanation meaningExplanation = entityMapper.toMeaningExplanation(meaningExplanationDto);
        meaningExplanationService.save(meaningExplanation);
        return meaningExplanationDto;
    }

    public void delete(Long meaningId, Long explanationId) {
        MeaningExplanationId id = MeaningExplanationId.builder()
                .meaningId(meaningId)
                .explanationId(explanationId)
                .build();
        meaningExplanationService.deleteById(id);
    }
}
