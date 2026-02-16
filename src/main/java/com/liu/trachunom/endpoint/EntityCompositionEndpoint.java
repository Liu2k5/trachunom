package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.EntityCompositionDto;
import com.liu.trachunom.entity.EntityComposition;
import com.liu.trachunom.entity.EntityCompositionId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.EntityCompositionService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class EntityCompositionEndpoint {
    private final EntityCompositionService entityCompositionService;
    private final EntityMapper entityMapper;

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

    public void delete(Long parentEntityId, Long childEntityId, Integer position) {
        EntityCompositionId id = EntityCompositionId.builder()
                .parentEntityId(parentEntityId)
                .childEntityId(childEntityId)
                .position(position.longValue())
                .build();
        entityCompositionService.deleteById(id);
    }
}
