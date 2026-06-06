package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.entity.entity.EvolutionDescription;
import com.liu.trachunom.service.entity.EvolutionDescriptionService;
import com.vaadin.hilla.BrowserCallable;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import java.util.List;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class EvolutionDescriptionEndpoint {
    private final EvolutionDescriptionService evolutionDescriptionService;

    public List<EvolutionDescription> findAll() {
        return evolutionDescriptionService.findAll();
    }
}
