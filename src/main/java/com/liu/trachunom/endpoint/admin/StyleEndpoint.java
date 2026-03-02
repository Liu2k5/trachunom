package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.StyleDto;
import com.liu.trachunom.entity.Style;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.StyleService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

@BrowserCallable
@RolesAllowed("ADMIN")
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
