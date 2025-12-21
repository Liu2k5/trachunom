package com.liu.trachunom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.entity.StructureComponent;
import com.liu.trachunom.entity.StructureComponentId;
import com.liu.trachunom.repository.SubStructureRepository;
import com.liu.trachunom.repository.StructureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StructureComponentService {
    private final SubStructureRepository subStructureRepository;
    private final StructureClassificationService structureClassificationService;
    private final StructureRepository structureRepository;

    public List<StructureComponent> findByStructure(Structure structure) {
        return subStructureRepository.findByStructure(structure);
    }

    public void save(List<StructureComponent> structureComponents) {
        for (int i = 0; i < structureComponents.size(); i++) {
            StructureComponent structureComponent = structureComponents.get(i);
            StructureComponent existingStructureComponent = subStructureRepository.findByStructure_IdAndStructureClassification_Id(
                    structureComponent.getStructure().getId(),
                    structureComponent.getStructureClassification().getId()
            ).stream()
            .filter((o) -> o.getId().getStructureComponentId().equals(structureComponent.getId().getStructureComponentId()))
            .findFirst().orElse(null);
            if (existingStructureComponent != null) {
                structureComponent.setQuantity(existingStructureComponent.getQuantity() + structureComponent.getQuantity());
            }
            subStructureRepository.save(structureComponent);
        }
    }

    public void save(StructureComponent structureComponent) {
        subStructureRepository.save(structureComponent);
    }

    public void deleteById(StructureComponentId id) {
        subStructureRepository.deleteById(id);
    }

    public void deleteByStructure(Structure structure) {
        subStructureRepository.deleteByStructure(structure);
    }

}
