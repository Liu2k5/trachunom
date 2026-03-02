package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.EntityCompositionDto;
import com.liu.trachunom.entity.entity.EntityComposition;
import com.liu.trachunom.entity.entity.EntityCompositionId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.entity.EntityCompositionService;
import com.liu.trachunom.service.entity.EntityService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class EntityCompositionEndpoint {
    private final EntityCompositionService entityCompositionService;
    private final EntityMapper entityMapper;
    private final EntityService entityService;

    public List<EntityCompositionDto> findByParentEntityId(Long parentEntityId) {
        return entityCompositionService.findByParentEntityId(parentEntityId).stream()
                .sorted(Comparator.comparingLong(o -> o.getId().getPosition()))
                .map(entityMapper::toEntityCompositionDto)
                .collect(Collectors.toList());
    }

    public EntityCompositionDto save(EntityCompositionDto dto) {
        EntityComposition composition = entityMapper.toEntityComposition(dto);
        entityCompositionService.save(composition);
        return entityMapper.toEntityCompositionDto(composition);
    }

    public EntityCompositionDto saveByIds(Long parentEntityId, Long childEntityId, Integer position) {
        EntityComposition composition = EntityComposition.builder()
                .id(EntityCompositionId.builder()
                        .parentEntityId(parentEntityId)
                        .childEntityId(childEntityId)
                        .position(position.longValue())
                        .build())
                .parentEntity(entityService.findById(parentEntityId))
                .childEntity(entityService.findById(childEntityId))
                .build();
        entityCompositionService.save(composition);
        return entityMapper.toEntityCompositionDto(composition);
    }

    public void delete(EntityCompositionId id) {
        entityCompositionService.deleteById(id);
    }

    public void deleteByIds(Long parentEntityId, Long childEntityId, Integer position) {
        EntityCompositionId id = EntityCompositionId.builder()
                .parentEntityId(parentEntityId)
                .childEntityId(childEntityId)
                .position(position.longValue())
                .build();
        entityCompositionService.deleteById(id);
    }
}
