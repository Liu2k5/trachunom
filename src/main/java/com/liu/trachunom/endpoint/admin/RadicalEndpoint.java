package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.RadicalDto;
import com.liu.trachunom.entity.character.Radical;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.character.RadicalService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class RadicalEndpoint {
    private final EntityMapper entityMapper;
    private final RadicalService radicalService;

    public List<RadicalDto> list() {
        return entityMapper.toRadicalDtoList(radicalService.findAll());
    }

    public Radical save(RadicalDto radicalDto) {
        Radical radical = entityMapper.toRadical(radicalDto);
        radicalService.save(radical);
        return radical;
    }

    public void delete(String id) {
        radicalService.deleteById(id);
    }

}
