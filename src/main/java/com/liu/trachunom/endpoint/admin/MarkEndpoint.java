package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.MarkDto;
import com.liu.trachunom.entity.evidence.Mark;
import com.liu.trachunom.entity.evidence.MarkId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.evidence.MarkService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class MarkEndpoint {
    private final EntityMapper entityMapper;
    private final MarkService markService;

    public MarkDto save(MarkDto markDto) {
        Mark mark = entityMapper.toMark(markDto);
        return entityMapper.toMarkDto(markService.save(mark));
    }

    public void delete(Long imageId, Long entityId) {
        MarkId markId = MarkId.builder()
                .imageId(imageId)
                .entityId(entityId)
                .build();
        markService.deleteById(markId);
    }

    public List<MarkDto> findAll() {
        return markService.findAll().stream()
                .map(entityMapper::toMarkDto)
                .collect(Collectors.toList());
    }
}

