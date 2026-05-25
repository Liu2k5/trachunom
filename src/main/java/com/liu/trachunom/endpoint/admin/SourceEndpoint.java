package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.SourceDto;
import com.liu.trachunom.entity.evidence.Source;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.evidence.SourceService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class SourceEndpoint {
    private final EntityMapper entityMapper;
    private final SourceService sourceService;

    public Source save(SourceDto sourceDto) {
        Source source = entityMapper.toSource(sourceDto);
        sourceService.save(source);
        return source;
    }

    public void delete(Long id) {
        sourceService.deleteById(id);
    }
}
