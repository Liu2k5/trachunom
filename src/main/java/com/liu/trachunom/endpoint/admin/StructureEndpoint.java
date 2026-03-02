package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.StructureComponentDto;
import com.liu.trachunom.dto.StructureDto;
import com.liu.trachunom.entity.structure.Structure;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.structure.StructureService;
import com.liu.trachunom.service.structure.StructureComponentService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureEndpoint {
    private final StructureService structureService;
    private final StructureComponentService structureComponentService;
    private final EntityMapper entityMapper;

    public StructureDto save(StructureDto structureDto) {
        Structure structure = entityMapper.toStructure(structureDto);
        Structure savedStructure = structureService.save(structure);
        return entityMapper.toStructureDto(savedStructure);
    }

    public void delete(Long id) {
        structureService.deleteById(id);
    }

    public List<StructureComponentDto> getStructureComponents(Long structureId) {
        Structure structure = structureService.findById(structureId);
        if (structure == null) {
            return List.of();
        }
        return structureComponentService.getStructureComponents(structure).stream()
                .map(entityMapper::toStructureComponentDto)
                .collect(Collectors.toList());
    }

    public StructureDto getStructureById(Long structureId) {
        Structure structure = structureService.findById(structureId);
        return entityMapper.toStructureDto(structure);
    }

    public List<StructureDto> findAll() {
        return structureService.findAll().stream()
                .map(entityMapper::toStructureDto)
                .collect(Collectors.toList());
    }
}
