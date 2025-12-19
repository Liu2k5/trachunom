package com.liu.trachunom.service;

import java.util.List;

import com.liu.trachunom.entity.StructureClassification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liu.trachunom.repository.ClassificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StructureClassificationService {
    private final ClassificationRepository classificationRepository;

    public List<StructureClassification> findAll() {
        return classificationRepository.findAll();
    }

    public StructureClassification findById(Long classificationId) {
        return classificationRepository.findById(classificationId).orElseThrow(() -> new RuntimeException("Phân loại không tồn tại"));
    }

    @Transactional
    public StructureClassification save(StructureClassification structureClassification) {
        return classificationRepository.save(structureClassification);
    }

    @Transactional
    public void delete(Long id) {
        classificationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return classificationRepository.existsById(id);
    }

    public StructureClassification getPhoneticClassification() {
        return classificationRepository.findByDescription("Biểu âm");
    }

    public StructureClassification getNoneClassification() {
        return classificationRepository.findByDescription("Không");
    }

    public StructureClassification getIdeographicClassification() {
        return classificationRepository.findByDescription("Biểu ý");
    }

    public boolean isPhoneticClassification(StructureClassification structureClassification) {
        if (structureClassification == null || structureClassification.getDescription() == null) {
            return false;
        }
        if (structureClassification.getDescription().equals("Biểu âm")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNoneClassification(StructureClassification structureClassification) {
        if (structureClassification == null || structureClassification.getDescription() == null) {
            return false;
        }
        if (structureClassification.getDescription().equals("Không")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isIdeographicClassification(StructureClassification structureClassification) {
        if (structureClassification == null || structureClassification.getDescription() == null) {
            return false;
        }
        if (structureClassification.getDescription().equals("Biểu ý")) {
            return true;
        } else {
            return false;
        }
    }

}
