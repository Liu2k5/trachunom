package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.StyleDto;
import com.liu.trachunom.entity.Style;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.StyleService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StyleEndpoint {
    private final EntityMapper entityMapper;
    private final StyleService styleService;

    public Style save(StyleDto styleDto) {
        Style style = entityMapper.toStyle(styleDto);
        styleService.save(style);
        return style;
    }

    public void delete(Long id) {
        styleService.deleteById(id);
    }
}
