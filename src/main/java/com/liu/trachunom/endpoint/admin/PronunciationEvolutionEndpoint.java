package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.PronunciationEvolutionDto;
import com.liu.trachunom.entity.pronunciation.PronunciationEvolution;
import com.liu.trachunom.entity.pronunciation.PronunciationEvolutionId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.pronunciation.PronunciationEvolutionService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class PronunciationEvolutionEndpoint {
    private final PronunciationEvolutionService pronunciationEvolutionService;
    private final EntityMapper entityMapper;

    public PronunciationEvolutionDto save(PronunciationEvolutionDto pronunciationEvolutionDto) {
        PronunciationEvolution pronunciationEvolution = entityMapper.toPronunciationEvolution(pronunciationEvolutionDto);
        pronunciationEvolutionService.save(pronunciationEvolution);
        return pronunciationEvolutionDto;
    }

    public void delete(PronunciationEvolutionId id) {
        pronunciationEvolutionService.deleteById(id);
    }
}
