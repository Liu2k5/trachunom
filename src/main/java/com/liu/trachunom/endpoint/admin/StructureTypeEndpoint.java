package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.entity.structure.StructureType;
import com.liu.trachunom.service.structure.StructureTypeService;
import com.vaadin.hilla.BrowserCallable;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import java.util.List;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class StructureTypeEndpoint {
    private final StructureTypeService structureTypeService;

    public List<StructureType> findAll() {
        return structureTypeService.findAll();
    }
}
