package com.liu.trachunom.service.structure;

import java.util.List;

import com.liu.trachunom.entity.structure.StructureComponent;
import com.liu.trachunom.entity.structure.StructureDescription;
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
            StringBuilder output = new StringBuilder();
            Structure description = structureDescriptionRepository.findByStructureId(id).getDescriptionStructure();
            return getStructureSequence(description.getId());
        } catch (Exception ignored) {
        }
        return "";
    }

}
