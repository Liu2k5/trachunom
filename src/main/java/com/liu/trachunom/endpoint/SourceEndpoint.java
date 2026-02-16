package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.SourceDto;
import com.liu.trachunom.entity.Source;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.SourceService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
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
