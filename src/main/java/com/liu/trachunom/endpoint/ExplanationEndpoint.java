package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.ExplanationDto;
import com.liu.trachunom.entity.Explanation;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.ExplanationService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class ExplanationEndpoint {
    private final ExplanationService explanationService;
    private final EntityMapper entityMapper;

    public Explanation save(ExplanationDto explanationDto) {
        Explanation explanation = entityMapper.toExplanation(explanationDto);
        explanationService.save(explanation);
        return explanation;
    }

    public void delete(Long id) {
        explanationService.deleteById(id);
    }
}
