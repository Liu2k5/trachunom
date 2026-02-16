package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.MeaningExplanationDto;
import com.liu.trachunom.entity.Meaning;
import com.liu.trachunom.entity.MeaningExplanation;
import com.liu.trachunom.entity.MeaningExplanationId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.MeaningExplanationService;
import com.liu.trachunom.service.MeaningService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class MeaningExplanationEndpoint {
    private final MeaningExplanationService meaningExplanationService;
    private final MeaningService meaningService;
    private final EntityMapper entityMapper;

    public List<MeaningExplanationDto> findByMeaningId(Long meaningId) {
        return meaningExplanationService.findByMeaning(meaningService.findById(meaningId)).stream()
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
