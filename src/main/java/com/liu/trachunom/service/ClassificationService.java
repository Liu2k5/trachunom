package com.liu.trachunom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liu.trachunom.entity.Classification;
import com.liu.trachunom.repository.ClassificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassificationService {
    private final ClassificationRepository classificationRepository;

    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    public Classification findById(Long classificationId) {
        return classificationRepository.findById(classificationId).orElseThrow(() -> new RuntimeException("Phân loại không tồn tại"));
    }

    @Transactional
    public Classification save(Classification classification) {
        return classificationRepository.save(classification);
    }

    @Transactional
    public void delete(Long id) {
        classificationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return classificationRepository.existsById(id);
    }

    public Classification getPhoneticClassification() {
        return classificationRepository.findByDescription("Biểu âm");
    }

    public Classification getNoneClassification() {
        return classificationRepository.findByDescription("Không");
    }

    public Classification getIdeographicClassification() {
        return classificationRepository.findByDescription("Biểu ý");
    }

    public boolean isPhoneticClassification(Classification classification) {
        if (classification == null || classification.getDescription() == null) {
            return false;
        }
        if (classification.getDescription().equals("Biểu âm")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNoneClassification(Classification classification) {
        if (classification == null || classification.getDescription() == null) {
            return false;
        }
        if (classification.getDescription().equals("Không")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isIdeographicClassification(Classification classification) {
        if (classification == null || classification.getDescription() == null) {
            return false;
        }
        if (classification.getDescription().equals("Biểu ý")) {
            return true;
        } else {
            return false;
        }
    }

}
