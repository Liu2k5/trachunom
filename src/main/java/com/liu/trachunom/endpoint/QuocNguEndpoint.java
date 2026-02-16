package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.QuocNguDto;
import com.liu.trachunom.entity.QuocNgu;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.QuocNguService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
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
