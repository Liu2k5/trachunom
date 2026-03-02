package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.CharacterDto;
import com.liu.trachunom.entity.character.CharacterX;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.character.CharacterService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
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
