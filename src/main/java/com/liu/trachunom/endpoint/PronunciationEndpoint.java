package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.PronunciationDto;
import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.PronunciationService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class PronunciationEndpoint {
    private final PronunciationService pronunciationService;
    private final EntityMapper entityMapper;

    public Pronunciation save(PronunciationDto pronunciationDto) {
        Pronunciation pronunciation = entityMapper.toPronunciation(pronunciationDto);
        pronunciationService.save(pronunciation);
        return pronunciation;
    }

    public void delete(Long id) {
        pronunciationService.deleteById(id);
    }
}
