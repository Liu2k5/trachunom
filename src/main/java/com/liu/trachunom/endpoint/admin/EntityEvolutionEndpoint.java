package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.EntityEvolutionDto;
import com.liu.trachunom.entity.entity.EntityEvolution;
import com.liu.trachunom.entity.entity.EntityEvolutionId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.entity.EntityEvolutionService;
import com.liu.trachunom.service.entity.EntityService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class EntityEvolutionEndpoint {
    private final EntityEvolutionService entityEvolutionService;
    private final EntityMapper entityMapper;
    private final EntityService entityService;

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

    public EntityEvolutionDto saveByIds(Long fromEntityId, Long toEntityId, String description) {
        EntityEvolutionId evolutionId = EntityEvolutionId.builder()
                .fromEntityId(fromEntityId)
                .toEntityId(toEntityId)
                .build();
        EntityEvolution evolution = EntityEvolution.builder()
                .id(evolutionId)
                .fromEntity(entityService.findById(fromEntityId))
                .toEntity(entityService.findById(toEntityId))
                .description(description)
                .build();
        entityEvolutionService.save(evolution);
        return entityMapper.toEntityEvolutionDto(evolution);
    }

    public void delete(EntityEvolutionId id) {
        entityEvolutionService.deleteById(id);
    }

    public void deleteByEachId(Long fromEntityId, Long toEntityId) {
        EntityEvolutionId id = new EntityEvolutionId(fromEntityId, toEntityId);
        entityEvolutionService.deleteById(id);
    }
}
