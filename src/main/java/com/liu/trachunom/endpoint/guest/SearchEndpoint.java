package com.liu.trachunom.endpoint.guest;

import com.liu.trachunom.entity.entity.EntityX;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.entity.EntityService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class SearchEndpoint {
    private final EntityService entityService;
    private final EntityMapper entityMapper;

    public List<EntityX> findByQuery(String query) {
        return entityService.findByQuery(query);
    }

}
