package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.LanguageDto;
import com.liu.trachunom.entity.Language;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.LanguageService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class LanguageEndpoint {
    private final LanguageService languageService;
    private final EntityMapper entityMapper;

    public Language save(LanguageDto languageDto) {
        Language language = entityMapper.toLanguage(languageDto);
        languageService.save(language);
        return language;
    }

    public void delete(Long id) {
        languageService.deleteById(id);
    }
}
