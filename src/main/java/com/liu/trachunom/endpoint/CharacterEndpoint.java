package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.CharacterDto;
import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.CharacterService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class CharacterEndpoint {
    private final EntityMapper entityMapper;
    private final CharacterService characterService;

    public CharacterX save(CharacterDto characterDto) {
        CharacterX characterX = entityMapper.toCharacter(characterDto);
        characterService.save(characterX);
        return characterX;
    }

    public void delete(Integer unicode) {
        characterService.deleteByUnicode(unicode);
    }
}
