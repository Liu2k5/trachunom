package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.EntityEvolutionDto;
import com.liu.trachunom.entity.EntityEvolution;
import com.liu.trachunom.entity.EntityEvolutionId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.EntityEvolutionService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class EntityEvolutionEndpoint {
    private final EntityEvolutionService entityEvolutionService;
    private final EntityMapper entityMapper;

    public List<EntityEvolutionDto> findByFromEntityId(Long fromEntityId) {
        return entityEvolutionService.findByFromEntityId(fromEntityId).stream()
                .map(entityMapper::toEntityEvolutionDto)
                .collect(Collectors.toList());
    }

    public EntityEvolutionDto save(EntityEvolutionDto dto) {
        EntityEvolution evolution = entityMapper.toEntityEvolution(dto);
        entityEvolutionService.save(evolution);
        return entityMapper.toEntityEvolutionDto(evolution);
    }

    public void delete(Long fromEntityId, Long toEntityId) {
        EntityEvolutionId id = new EntityEvolutionId(fromEntityId, toEntityId);
        entityEvolutionService.deleteById(id);
    }
}
