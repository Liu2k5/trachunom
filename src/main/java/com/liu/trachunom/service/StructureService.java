package com.liu.trachunom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.liu.trachunom.entity.SubStructure;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.stereotype.Service;

import com.liu.trachunom.dto.StructureDto;
import com.liu.trachunom.dto.StructureFormDto;
import com.liu.trachunom.dto.SubStructureDto;
import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.repository.StructureRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StructureService {

    private final CharacterService characterService;
    private final StructureRepository structureRepository;
    private final SubStructureService subStructureService;
    private final VisualTool visualTool;

    public Structure findById(Long id) {
        return structureRepository.findById(id).orElse(null);
    }

    public Structure parse(StructureFormDto structureFormDto) {
        if (structureFormDto == null) {
            return null;
        }
        StructureDto structureDto = structureFormDto.getStructureDto();
        String ch = structureDto.getString();
        Integer unicode = null;
        if (ch != null && !ch.isEmpty()) {
            // safe for supplementary characters
            int codePoint = ch.codePointAt(0);
            unicode = codePoint;
        }
        return Structure.builder()
            .id(structureDto.getId())
            .character(unicode == null ? null : characterService.findByUnicode(unicode))
            .build();
    }

    public void save(Structure structure) {
        structureRepository.save(structure);
    }

    public StructureFormDto toFormDto(Structure structure) {
        StructureFormDto formDto = new StructureFormDto();
        if (structure == null) {
            return formDto;
        }
        StructureDto structureDto = StructureDto.builder()
            .id(structure.getId())
            // Convert the stored unicode code point (int) to a Java String correctly,
            // using Character.toChars to support supplementary characters (> 0xFFFF).
            .string(structure.getCharacter() == null ? "" : new String(Character.toChars(structure.getCharacter().getUnicode())))
            .build();

        List<SubStructureDto> subStructureDtos = new ArrayList<>();
        for (SubStructure subStructure : subStructureService.findByStructure(structure)) {
            subStructureDtos.add(SubStructureDto.builder()
                .subStructureId(subStructure.getId().getSubStructureId())
                .classificationId(subStructure.getStructureClassification().getId())
                .quantity(subStructure.getQuantity())
                .string(getCharacterStringById(subStructure.getId().getSubStructureId()))
                .build());
        }

        formDto.setSubStructures(subStructureDtos);
        formDto.setStructureDto(structureDto);
        return formDto;
    }

    private String getCharacterStringById(Long id) {
        Structure structure = findById(id);
        if (structure != null && structure.getCharacter() != null) {
            return structure.getCharacter().getString();
        }
        return null;
    }

    @Transactional
    public void save(StructureFormDto structureFormDto) {
        // kiem tra cac cau tao tuong tu da luu

        StructureDto structureDto = structureFormDto.getStructureDto();
        if (structureDto.getString() == null || structureDto.getString().isEmpty()) {
            throw new RuntimeException("Trường kí tự không được để trống");
        }

        List<Structure> similarStructures = structureRepository.findByCharacter(characterService.findByUnicode(structureDto.getString().codePointAt(0)));
        List<SubStructureDto> subStructureDtos = structureFormDto.getSubStructures();
        subStructureDtos = trim(subStructureDtos);
        for (Structure structure : similarStructures) {
            List<SubStructure> subStructures = subStructureService.findByStructure(structure);
            if (isSameStructure(subStructures, subStructureDtos)) {
                throw new RuntimeException("Cấu trúc tương tự đã tồn tại với id = " + structure.getId());
            }
        }

        Structure structure = null;
        if (structureDto.getId() != null) {
            structureRepository.findById(structureDto.getId()).orElse(null);
        }
        if (structure != null) {
            // xoa cau tao con cu trong cung transaction
            subStructureService.deleteByStructure(structure);
            // flush để đảm bảo delete được thực thi trước khi insert
            structureRepository.flush();
        } else {
            structure = parse(structureFormDto);
            // save structure trước để có ID
            save(structure);
        }

        // parse và save subStructures với structure đã có ID
        if (structure != null && structure.getId() != null) {
            List<SubStructure> subStructures = subStructureService.parseList(subStructureDtos, structure);
            subStructureService.save(subStructures);
        }

    }

    private List<SubStructureDto> trim(List<SubStructureDto> subStructureDtos) {
        List<SubStructureDto> temp = new ArrayList<>();
        for (SubStructureDto subStructureDto : subStructureDtos) {
            if (!(subStructureDto.getSubStructureId() == null ||
                    subStructureDto.getClassificationId() == null ||
                    subStructureDto.getQuantity() == null ||
                    subStructureDto.getQuantity() <= 0)) {
                temp.add(subStructureDto);
            }
        }
        return temp;
    }

    private boolean isSameStructure(List<SubStructure> subStructures, List<SubStructureDto> subStructureDtos) {
        // kich thuoc subStructureDto luon = 10 nen khong so sanh size
        subStructures.sort((o1, o2) -> {
            int comp = (int) (o1.getId().getSubStructureId() - o2.getId().getSubStructureId());
            if (comp != 0) {
                return comp;
            }
            comp = (int) (o1.getStructureClassification().getId() - o2.getStructureClassification().getId());
            if (comp != 0) {
                return comp;
            }
            return o1.getQuantity() - o2.getQuantity();
        });
        subStructureDtos.sort((o1, o2) -> {
            int comp = (int) (o1.getSubStructureId() - o2.getSubStructureId());
            if (comp != 0) {
                return comp;
            }
            comp = (int) (o1.getClassificationId() - o2.getClassificationId());
            if (comp != 0) {
                return comp;
            }
            return o1.getQuantity() - o2.getQuantity();
        });

        for (int i = 0; i < subStructures.size(); i++) {
            SubStructure subStructure = subStructures.get(i);
            SubStructureDto subStructureDto = subStructureDtos.get(i);
            if (!(Objects.equals(subStructure.getId().getSubStructureId(), subStructureDto.getSubStructureId()) &&
                    Objects.equals(subStructure.getId().getClassificationId(), subStructureDto.getClassificationId()) &&
                    Objects.equals(subStructure.getQuantity(), subStructureDto.getQuantity()))) {
                return false;
            }
        }
        return true;
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
        return drawStructure(structure.getSubStructures());
    }

    public HorizontalLayout drawStructure(List<SubStructure> structures) {
        return visualTool.drawStructure(structures);
    }

}
