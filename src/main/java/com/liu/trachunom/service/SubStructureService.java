package com.liu.trachunom.service;

import java.util.List;

import com.liu.trachunom.entity.Classification;
import org.springframework.stereotype.Service;

import com.liu.trachunom.dto.SubStructureDto;
import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.entity.SubStructure;
import com.liu.trachunom.entity.SubStructureId;
import com.liu.trachunom.repository.SubStructureRepository;
import com.liu.trachunom.repository.StructureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubStructureService {
    private final SubStructureRepository subStructureRepository;
    private final ClassificationService classificationService;
    private final StructureRepository structureRepository;

    public SubStructure parse(SubStructureDto subStructureDto, Structure structure) {
        if (subStructureDto == null || structure == null) {
            return null;
        }

        Long subStructureId = subStructureDto.getSubStructureId();
        if (!structureRepository.existsById(subStructureId)) {
            throw new RuntimeException("Cấu tạo con không tồn tại với id = " +  subStructureId);
        }
        Classification classification = classificationService.findById(subStructureDto.getClassificationId());
        SubStructure subStructure = SubStructure.builder()
                .id(SubStructureId.builder()
                        .structureId(structure.getId())
                        .subStructureId(subStructureId)
                        .classificationId(classification.getId())
                        .build())
                .structure(structure)
                .classification(classification)
                .quantity(subStructureDto.getQuantity())
                .build();
        return subStructure;
    }

    public List<SubStructure> parseList(List<SubStructureDto> subStructureDtos, Structure structure) {
        return subStructureDtos.stream()
                .map(dto -> parse(dto, structure))
                .toList();
    }

    public List<SubStructure> findByStructure(Structure structure) {
        return subStructureRepository.findByStructure(structure);
    }

    public void save(List<SubStructure> subStructures) {
        for (int i = 0; i < subStructures.size(); i++) {
            SubStructure subStructure = subStructures.get(i);
            SubStructure existingSubStructure = subStructureRepository.findByStructure_IdAndClassification_Id(
                    subStructure.getStructure().getId(),
                    subStructure.getClassification().getId()
            ).stream()
            .filter((o) -> o.getId().getSubStructureId().equals(subStructure.getId().getSubStructureId()))
            .findFirst().orElse(null);
            if (existingSubStructure != null) {
                subStructure.setQuantity(existingSubStructure.getQuantity() + subStructure.getQuantity());
            }
            subStructureRepository.save(subStructure);
        }
    }

    public void save(SubStructure subStructure) {
        subStructureRepository.save(subStructure);
    }

    public void deleteById(SubStructureId id) {
        subStructureRepository.deleteById(id);
    }

    public void deleteByStructure(Structure structure) {
        subStructureRepository.deleteByStructure(structure);
    }

}
