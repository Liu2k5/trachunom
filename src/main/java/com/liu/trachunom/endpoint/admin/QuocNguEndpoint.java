package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.QuocNguDto;
import com.liu.trachunom.entity.pronunciation.QuocNgu;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.pronunciation.QuocNguService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class QuocNguEndpoint {
    private final QuocNguService quocNguService;
    private final EntityMapper entityMapper;

    public QuocNguDto save(QuocNguDto quocNguDto) {
        QuocNgu quocNgu = entityMapper.toQuocNgu(quocNguDto);
        quocNguService.save(quocNgu);
        return entityMapper.toQuocNguDto(quocNgu);
    }

    public void delete(Long id) {
        quocNguService.deleteById(id);
    }
}
