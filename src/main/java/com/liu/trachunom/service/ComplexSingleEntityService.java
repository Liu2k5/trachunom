package com.liu.trachunom.service;

import com.liu.trachunom.entity.ComplexSingleEntity;
import com.liu.trachunom.entity.ComplexSingleEntityId;
import com.liu.trachunom.repository.ComplexSingleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplexSingleEntityService {
    private final ComplexSingleEntityRepository complexSingleEntityRepository;

    public ComplexSingleEntity findById(ComplexSingleEntityId id) {
        return complexSingleEntityRepository.findById(id).orElse(null);
    }

    public List<ComplexSingleEntity> findAll() {
        return complexSingleEntityRepository.findAll();
    }

    @Transactional
    public ComplexSingleEntity save(ComplexSingleEntity complexSingleEntity) {
        return complexSingleEntityRepository.save(complexSingleEntity);
    }

    @Transactional
    public void delete(ComplexSingleEntityId id) {
        complexSingleEntityRepository.deleteById(id);
    }

    public boolean existsById(ComplexSingleEntityId id) {
        return complexSingleEntityRepository.existsById(id);
    }
}

