package com.liu.trachunom.service;

import java.util.List;

import com.liu.trachunom.entity.StructureComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.repository.StructureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StructureService {

    private final CharacterService characterService;
    private final StructureRepository structureRepository;
    private final StructureComponentService structureComponentService;
    private final VisualTool visualTool;

    public Structure findById(Long id) {
        return structureRepository.findById(id).orElse(null);
    }

    public void save(Structure structure) {
        structureRepository.save(structure);
    }

    private String getCharacterStringById(Long id) {
        Structure structure = findById(id);
        if (structure != null && structure.getCharacter() != null) {
            return structure.getCharacter().getString();
        }
        return null;
    }

    public boolean existsById(Long structureId) {
        return structureRepository.existsById(structureId);
    }

    public List<Structure> findAll() {
//        // Use fetch-join repository method to load subStructures eagerly for UI
//        return structureRepository.findAllWithSubStructures();
        return structureRepository.findAll();
    }

    public void deleteById(Long id) {
        structureRepository.deleteById(id);
    }

    public HorizontalLayout drawStructure(Structure structure) {
        if (structure == null) {
            return new HorizontalLayout();
        }
        return drawStructure(structure.getStructureComponents());
    }

    public HorizontalLayout drawStructure(List<StructureComponent> structures) {
        return visualTool.drawStructure(structures);
    }

}
