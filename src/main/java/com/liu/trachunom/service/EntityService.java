package com.liu.trachunom.service;

import com.liu.trachunom.entity.CharacterX;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.liu.trachunom.dto.EntityDto;
import com.liu.trachunom.entity.EntityX;
import com.liu.trachunom.repository.EntityRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityService {
    private final EntityRepository entityRepository;
    private final MeaningService meaningService;
    private final StructureService structureService;
    private final PronunciationService pronunciationService;
    private final LanguageService languageService;

    public EntityX findById(Long id) {
        return entityRepository.findById(id).orElse(null);
    }

    public EntityDto toDto(EntityX entity) {
        if (entity == null) {
            return null;
        }
        return EntityDto.builder()
                .id(entity.getId())
                .meaningId(entity.getMeaning() != null ? entity.getMeaning().getId() : null)
                .structureId(entity.getStructure() != null ? entity.getStructure().getId() : null)
                .pronunciationId(entity.getPronunciation() != null ? entity.getPronunciation().getId() : null)
                .languageId(entity.getLanguage() != null ? entity.getLanguage().getId() : null)
                .description(entity.getDescription())
                .build();
    }

    public EntityX parse(EntityDto entityDto) {
        if (entityDto == null) {
            return null;
        }
        if (entityDto.getMeaningId() == null ||
            entityDto.getStructureId() == null) {
            throw new RuntimeException("Id ý nghĩa hoặc cấu trúc không được để trống");
        }
        if (!meaningService.existsById(entityDto.getMeaningId())) {
            throw new RuntimeException("Ý nghĩa không tồn tại");
        }
        if (!structureService.existsById(entityDto.getStructureId())) {
            throw new RuntimeException("Cấu trúc không tồn tại");
        }

        return EntityX.builder()
                .id(entityDto.getId())
                .meaning(meaningService.findById(entityDto.getMeaningId()))
                .structure(structureService.findById(entityDto.getStructureId()))
                .pronunciation(entityDto.getPronunciationId() != null ? pronunciationService.findById(entityDto.getPronunciationId()) : null)
                .language(entityDto.getLanguageId() != null ? languageService.findById(entityDto.getLanguageId()) : null)
                .description(entityDto.getDescription())
                .build();
    }

    public void save(EntityX entity) {
        entityRepository.save(entity);
    }

    @Transactional
    public void save(EntityDto entityDto) {
        EntityX entity = parse(entityDto);
        entityRepository.save(entity);
    }

    public List<EntityX> findByCharacter(CharacterX character) {
        return entityRepository.findByStructure_Character(character);
    }

    public boolean existsById(Long entityId) {
        return entityRepository.existsById(entityId);
    }

    public List<EntityX> findAll() {
        return entityRepository.findAll();
    }
}
