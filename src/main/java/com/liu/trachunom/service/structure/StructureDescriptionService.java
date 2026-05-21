package com.liu.trachunom.service.structure;

import com.liu.trachunom.entity.structure.StructureDescription;
import com.liu.trachunom.repository.StructureDescriptionRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureDescriptionService extends ListRepositoryService<StructureDescription, Long, StructureDescriptionRepository> {

    private final StructureDescriptionRepository structureDescriptionRepository;

    public StructureDescription findByStructureId(Long structureId) {
        return structureDescriptionRepository.findByStructureId(structureId);
    }

    public List<StructureDescription> findAll() {
        return structureDescriptionRepository.findAll();
    }

    public StructureDescription save(StructureDescription structureDescription) {
        return structureDescriptionRepository.save(structureDescription);
    }

    public void deleteByStructureId(Long structureId) {
        StructureDescription description = findByStructureId(structureId);
        if (description != null) {
            structureDescriptionRepository.delete(description);
        }
    }
}
