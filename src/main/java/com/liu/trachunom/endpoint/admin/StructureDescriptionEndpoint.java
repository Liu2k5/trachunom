package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.StructureDescriptionDto;
import com.liu.trachunom.entity.structure.StructureDescription;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.structure.StructureDescriptionService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureDescriptionEndpoint {
    private final StructureDescriptionService structureDescriptionService;
    private final EntityMapper entityMapper;

    public StructureDescriptionDto save(StructureDescriptionDto structureDescriptionDto) {
        StructureDescription structureDescription = entityMapper.toStructureDescription(structureDescriptionDto);
        StructureDescription saved = structureDescriptionService.save(structureDescription);
        return entityMapper.toStructureDescriptionDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        structureDescriptionService.deleteByStructureId(id);
    }
}
