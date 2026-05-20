package com.liu.trachunom.service.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.liu.trachunom.entity.structure.StructureComponent;
import com.liu.trachunom.repository.StructureComponentRepository;
import com.liu.trachunom.repository.StructureDescriptionRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.structure.Structure;
import com.liu.trachunom.repository.StructureRepository;

import lombok.RequiredArgsConstructor;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureService extends ListRepositoryService<Structure, Long, StructureRepository> {

    private final StructureRepository structureRepository;
    private final StructureComponentService structureComponentService;
    private final StructureDescriptionRepository structureDescriptionRepository;
    private final StructureComponentRepository structureComponentRepository;

    public Structure findById(Long id) {
        if (id == null) {
            return null;
        }
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
            List<StructureComponent> components = structureComponentService.findByStructureId(structure.getId());
            StringBuilder characterString = new StringBuilder();
            for (StructureComponent component : components) {
                if (component.getStructureComponent().getCharacter() != null) {
                    characterString.append(component.getStructureComponent().getCharacter().getString());
                } else {
                    characterString.append(getCharacterStringById(component.getStructureComponent().getId()));
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

    public String getStructureSequence(Long id) {
        try {
            StringBuilder output = new StringBuilder();
            Structure structure = findById(id);
            if (structure.getStructureType() != null) {
                output.append(structure.getStructureType().getDescription());
            }
            List<StructureComponent> components = structureComponentService.findByStructureId(structure.getId());
            for (StructureComponent component : components) {
                if (component.getStructureComponent() != null) {
                    output.append(component.getStructureComponent().getCharacter().getString());
                }
            }
            return output.toString();
        } catch (Exception ignored) {
        }
        return "";
    }

    public String getIds(Long id) {
        try {
            Structure description = structureDescriptionRepository.findByStructureId(id).getDescriptionStructure();
            return getStructureSequence(description.getId());
        } catch (Exception ignored) {
        }
        return "";
    }

    public List<Structure> getAllVariantStructuresByStructureId(Long structureId) {
        if (structureId == null) {
            return List.of();
        }

        Structure structure = findById(structureId);
        List<Structure> foundStructures = new ArrayList<>();
        foundStructures.add(structure);
        Queue<Structure> queue = new LinkedList<>();
        queue.add(structure);
        while (!queue.isEmpty()) {
            Structure current = queue.poll();

            // find structure-component relationships where current is the structure / component respectively
            List<StructureComponent> structureSide = structureComponentRepository.findByStructureId(current.getId());
            // notice here ----------------------------------------------------------------------
            if (structureSide.size() != 1) {
                break;
            }

//            System.out.println("number of structure-side: " + structureSide.size());

            // assure that the relationships are neither semantic nor phonetic
            structureSide = structureSide.stream().filter(o -> o.getStructureClassification().getId() == 0).toList();

            // fetch components and structures from structure-side and component-side data
            List<Structure> components = structureSide.stream().map(StructureComponent::getStructureComponent).toList();

            queue.addAll(components);

            foundStructures.addAll(components);
        }

        queue.add(structure);
        while (!queue.isEmpty()) {
            Structure current = queue.poll();

            // find structure-component relationships where current is the structure / component respectively
            List<StructureComponent> componentSide = structureComponentRepository.findByStructureComponentId(current.getId());

//            System.out.println("number of component-side: " + componentSide.size());

            // assure that the relationships are neither semantic nor phonetic
            componentSide = componentSide.stream().filter(o -> o.getStructureClassification().getId() == 0).toList();

            // fetch components and structures from structure-side and component-side data
            List<Structure> structures = componentSide.stream().map(StructureComponent::getStructure).toList();

            // notice here ----------------------------------------------------------------------
            structures = structures.stream().filter(o -> structureComponentRepository
                    .findByStructureId(o.getId()).size() == 1)
                    .toList();

            queue.addAll(structures);

            foundStructures.addAll(structures);
        }
//        for (int i = 0; i < foundStructures.size(); i++) {
//            System.out.print(foundStructures.get(i).getCharacterString() + " ");
//        }
//        System.out.println();
        return foundStructures;
    }
}
