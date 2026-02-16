package com.liu.trachunom.service;

import java.util.List;

import com.liu.trachunom.entity.StructureComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.repository.StructureRepository;

import lombok.RequiredArgsConstructor;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureService extends ListRepositoryService<Structure, Long, StructureRepository> {

    private final CharacterService characterService;
    private final StructureRepository structureRepository;
    private final StructureComponentService structureComponentService;

    public Structure findById(Long id) {
        return structureRepository.findById(id).orElse(null);
    }

    public Structure save(Structure structure) {
        return structureRepository.save(structure);
    }

    public String getCharacterStringById(Long id) {
        Structure structure = findById(id);
        if (structure == null) {
            return null;
        }
        if (structure.getCharacter() != null) {
            return structure.getCharacter().getString();
        } else {
            List<StructureComponent> components = structureComponentService.findByStructure(structure);
            StringBuilder characterString = new StringBuilder();
            for (StructureComponent component : components) {
                if (component.getStructureComponent().getCharacter() != null) {
                    characterString.append(component.getStructureComponent().getCharacter().getString());
                }
            }
            return "[" + characterString.toString() + "]";
        }
    }

    public boolean existsById(Long structureId) {
        return structureRepository.existsById(structureId);
    }

    public List<Structure> findAll() {
        return structureRepository.findAll();
    }

    public void deleteById(Long id) {
        structureRepository.deleteById(id);
    }

}
