package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.PronunciationEvolutionDto;
import com.liu.trachunom.entity.PronunciationEvolution;
import com.liu.trachunom.entity.PronunciationEvolutionId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.PronunciationEvolutionService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
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
