package com.liu.trachunom.service.structure;

import java.util.List;

import com.liu.trachunom.entity.structure.StructureClassification;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liu.trachunom.repository.StructureClassificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureClassificationService extends ListRepositoryService<StructureClassification, Long, StructureClassificationRepository> {
    private final StructureClassificationRepository structureClassificationRepository;

    public List<StructureClassification> findAll() {
        return structureClassificationRepository.findAll();
    }

    public StructureClassification findById(Long classificationId) {
        return structureClassificationRepository.findById(classificationId).orElseThrow(() -> new RuntimeException("Phân loại không tồn tại"));
    }

    @Transactional
    public StructureClassification save(StructureClassification structureClassification) {
        return structureClassificationRepository.save(structureClassification);
    }

    @Transactional
    public void delete(Long id) {
        structureClassificationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return structureClassificationRepository.existsById(id);
    }

    public StructureClassification getPhoneticClassification() {
        return structureClassificationRepository.findByDescription("Biểu âm");
    }

    public StructureClassification getNoneClassification() {
        return structureClassificationRepository.findByDescription("Không");
    }

    public StructureClassification getIdeographicClassification() {
        return structureClassificationRepository.findByDescription("Biểu ý");
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
    public boolean isSemanticClassification(StructureClassification structureClassification) {
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
