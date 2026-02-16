package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.EntityDto;
import com.liu.trachunom.entity.EntityX;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.EntityService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class EntityEndpoint {
    private final EntityService entityService;
    private final EntityMapper entityMapper;

    public List<EntityDto> findAll() {
        return entityService.findAll().stream()
                .map(entityMapper::toEntityDto)
                .collect(Collectors.toList());
    }

    public EntityDto save(EntityDto entityDto) {

        EntityX entity = entityMapper.toEntityX(entityDto);
        entityService.save(entity);
        return entityMapper.toEntityDto(entity);
    }

    public void delete(Long id) {
        entityService.deleteById(id);
    }
}
