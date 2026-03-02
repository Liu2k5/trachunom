package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.StructureComponentDto;
import com.liu.trachunom.entity.structure.StructureComponent;
import com.liu.trachunom.entity.structure.StructureComponentId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.structure.StructureComponentService;
import com.liu.trachunom.service.structure.StructureService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@BrowserCallable
@RolesAllowed("ADMIN")
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
