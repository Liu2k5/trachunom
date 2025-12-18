package com.liu.trachunom.service;

import com.liu.trachunom.entity.ComplexEntity;
import com.liu.trachunom.repository.ComplexEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplexEntityService {
    private final ComplexEntityRepository complexEntityRepository;

    public ComplexEntity findById(Long id) {
        return complexEntityRepository.findById(id).orElse(null);
    }

    public List<ComplexEntity> findAll() {
        return complexEntityRepository.findAll();
    }

    @Transactional
    public ComplexEntity save(ComplexEntity complexEntity) {
        return complexEntityRepository.save(complexEntity);
    }

    @Transactional
    public void delete(Long id) {
        complexEntityRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return complexEntityRepository.existsById(id);
    }
}

