package com.liu.trachunom.service.structure;

import com.liu.trachunom.entity.structure.StructureType;
import com.liu.trachunom.repository.StructureTypeRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StructureTypeService {
    private final StructureTypeRepository structureTypeRepository;

    public List<StructureType> findAll() {
        return structureTypeRepository.findAll();
    }

    public StructureType findById(Long id) {
        return structureTypeRepository.findById(id).orElse(null);
    }
}
