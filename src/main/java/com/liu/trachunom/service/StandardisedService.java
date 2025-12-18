package com.liu.trachunom.service;

import com.liu.trachunom.dto.StandardisedDto;
import com.liu.trachunom.entity.StandardisedEntity;
import com.liu.trachunom.repository.StandardisedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StandardisedService {
    private final StandardisedRepository standardisedRepository;
    private final EntityService entityService;

    public StandardisedEntity findById(Long id) {
        return standardisedRepository.findById(id).orElse(null);
    }

    public List<StandardisedEntity> findAll() {
        return standardisedRepository.findAll();
    }

    public StandardisedDto toDto(StandardisedEntity standardisedEntity) {
        StandardisedDto standardisedDto = new StandardisedDto();
        if (standardisedEntity == null) return standardisedDto;

        standardisedDto = StandardisedDto.builder()
                .standardisedEntityId(standardisedEntity.getId())
                .entityId(standardisedEntity.getEntity().getId())
                .build();

        return standardisedDto;
    }

    public StandardisedEntity parse(StandardisedDto standardisedDto) {
        if (standardisedDto.getEntityId() == null) {
            throw new RuntimeException("Thực thể không được để trống.");
        }
        if (!entityService.existsById(standardisedDto.getEntityId())) {
            throw new RuntimeException("Thực thể không tồn tại với id: " + standardisedDto.getEntityId());
        }
        if (standardisedRepository.existsByEntity_Id(standardisedDto.getEntityId())) {
            throw new RuntimeException("Thực thể đã được chuẩn hóa trước đó.");
        }
        return StandardisedEntity.builder()
                .id(standardisedDto.getStandardisedEntityId())
                .entity(entityService.findById(standardisedDto.getEntityId()))
                .build();
    }

    @Transactional
    public void save(StandardisedDto standardisedDto) {
        StandardisedEntity standardisedEntity = parse(standardisedDto);
        save(standardisedEntity);
    }

    public void save(StandardisedEntity standardisedEntity) {
        standardisedRepository.save(standardisedEntity);
    }

    @Transactional
    public void delete(Long id) {
        standardisedRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return standardisedRepository.existsById(id);
    }
}
