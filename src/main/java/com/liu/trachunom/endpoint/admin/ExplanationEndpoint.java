package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.ExplanationDto;
import com.liu.trachunom.entity.meaning.Explanation;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.meaning.ExplanationService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
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
