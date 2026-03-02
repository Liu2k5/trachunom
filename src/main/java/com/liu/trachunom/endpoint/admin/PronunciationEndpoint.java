package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.PronunciationDto;
import com.liu.trachunom.entity.pronunciation.Pronunciation;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.pronunciation.PronunciationService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
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
