package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.entity.structure.StructureClassification;
import com.liu.trachunom.service.structure.StructureClassificationService;
import com.vaadin.hilla.BrowserCallable;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import java.util.List;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class StructureClassificationEndpoint {
    private final StructureClassificationService structureClassificationService;

    public List<StructureClassification> findAll() {
        return structureClassificationService.findAll();
    }
}
