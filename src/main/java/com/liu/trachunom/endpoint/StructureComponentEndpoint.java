package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.StructureComponentDto;
import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.entity.StructureComponent;
import com.liu.trachunom.entity.StructureComponentId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.StructureComponentService;
import com.liu.trachunom.service.StructureService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureComponentEndpoint {
    private final StructureComponentService structureComponentService;
    private final EntityMapper entityMapper;
    private final StructureService structureService;

    public StructureComponentDto save(StructureComponentDto structureComponentDto) {
        StructureComponent structureComponent = entityMapper.toStructureComponent(structureComponentDto);
        structureComponentService.save(structureComponent);
        return entityMapper.toStructureComponentDto(structureComponent);
    }

    @Transactional
    public void delete(StructureComponentId id)  {
        structureComponentService.deleteById(id);
    }


}
