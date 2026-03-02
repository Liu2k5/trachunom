package com.liu.trachunom.service.structure;

import java.util.List;
import java.util.stream.Collectors;

import com.liu.trachunom.entity.structure.StructureComponentId;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.structure.Structure;
import com.liu.trachunom.entity.structure.StructureComponent;
import com.liu.trachunom.repository.StructureComponentRepository;
import com.liu.trachunom.repository.StructureRepository;

import lombok.RequiredArgsConstructor;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureComponentService extends ListRepositoryService<StructureComponent, StructureComponentId, StructureComponentRepository> {
    private final StructureComponentRepository structureComponentRepository;
    private final StructureClassificationService structureClassificationService;
    private final StructureRepository structureRepository;

    public List<StructureComponent> findByStructure(Structure structure) {
        return structureComponentRepository.findByStructure(structure);
    }

    public List<StructureComponent> getStructureComponents(Structure structure) {
        if (structure == null || structure.getId() == null) {
            return List.of();
        }

        List<StructureComponent> components = structureComponentRepository.findByStructureId(structure.getId());

        return components.stream()
                .sorted((o1, o2) -> {
                    long classificationId1 = o1.getStructureClassification().getId();
                    long classificationId2 = o2.getStructureClassification().getId();
                    if (classificationId1 == classificationId2) {
                        return 0;
                    }
                    if (classificationId1 == 1 || classificationId2 == 1) {
                        if (classificationId1 == 1) return -1;
                        else return 1;
                    }
                    if (classificationId1 == -1 || classificationId2 == -1) {
                        if (classificationId1 == -1) return -1;
                        else return 1;
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }

    public void save(List<StructureComponent> structureComponents) {
        for (int i = 0; i < structureComponents.size(); i++) {
            StructureComponent structureComponent = structureComponents.get(i);
            StructureComponent existingStructureComponent = structureComponentRepository.findByStructure_IdAndStructureClassification_Id(
                    structureComponent.getStructure().getId(),
                    structureComponent.getStructureClassification().getId()
            ).stream()
            .filter((o) -> o.getStructureComponent() != null &&
                          o.getStructureComponent().getId() != null &&
                          o.getStructureComponent().getId().equals(structureComponent.getStructureComponent().getId()))
            .findFirst().orElse(null);
            if (existingStructureComponent != null) {
                structureComponent.setQuantity(existingStructureComponent.getQuantity() + structureComponent.getQuantity());
            }
            structureComponentRepository.save(structureComponent);
        }
    }

    public void save(StructureComponent structureComponent) {
        structureComponentRepository.save(structureComponent);
    }

    public void deleteById(StructureComponentId id) {
        structureComponentRepository.deleteById(id);
    }

    public void deleteByStructure(Structure structure) {
        structureComponentRepository.deleteByStructure(structure);
    }

    public StructureComponent findById(StructureComponentId id) {
        return structureComponentRepository.findById(id).orElse(null);
    }

    public List<StructureComponent> findAll() {
        return structureComponentRepository.findAll();
    }
}
