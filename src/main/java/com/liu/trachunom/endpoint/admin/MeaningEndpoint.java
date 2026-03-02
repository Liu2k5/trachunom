package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.MeaningDto;
import com.liu.trachunom.entity.meaning.Meaning;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.meaning.MeaningService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
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
