package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.MeaningDto;
import com.liu.trachunom.entity.Meaning;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.MeaningService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class MeaningEndpoint {
    private final MeaningService meaningService;
    private final EntityMapper entityMapper;

    public Meaning save(MeaningDto meaningDto) {
        Meaning meaning = entityMapper.toMeaning(meaningDto);
        meaningService.save(meaning);
        return meaning;
    }

    public void delete(Long id) {
        meaningService.deleteById(id);
    }
}
