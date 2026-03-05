package com.liu.trachunom.service;

import com.liu.trachunom.entity.Source;
import com.liu.trachunom.entity.entity.EntityComposition;
import com.liu.trachunom.entity.entity.EntityX;
import com.liu.trachunom.entity.example.Example;
import com.liu.trachunom.entity.example.ExampleWord;
import com.liu.trachunom.repository.EntityCompositionRepository;
import com.liu.trachunom.repository.EntityRepository;
import com.liu.trachunom.repository.ExampleWordRepository;
import com.liu.trachunom.repository.SourceRepository;
import com.liu.trachunom.service.entity.EntityService;
import com.liu.trachunom.service.example.ExampleWordService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class SourceService extends ListRepositoryService<Source, Long, SourceRepository> {
    private final SourceRepository sourceRepository;
    private final ExampleWordRepository exampleWordRepository;
    private final EntityRepository entityRepository;
    private final EntityCompositionRepository entityCompositionRepository;

    public Source findById(Long id) {
        return sourceRepository.findById(id).orElse(null);
    }

    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

    @Transactional
    public Source save(Source source) {
        return sourceRepository.save(source);
    }

    @Transactional
    public void delete(Long id) {
        sourceRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return sourceRepository.existsById(id);
    }

    public void deleteById(Long id) {
        sourceRepository.deleteById(id);
    }

    public List<Source> findByEntityId(Long entityId) {
        EntityX entityX = entityRepository.findById(entityId).orElse(null);
        if (entityX == null) {
            return List.of();
        }
        List<EntityX> relatedEntities = new ArrayList<>();
        relatedEntities.add(entityX);
        Queue<EntityX> queue = new LinkedList<>();
        queue.add(entityX);
        while (!queue.isEmpty()) {
            EntityX current = queue.poll();
            List<EntityComposition> compositions = entityCompositionRepository.findByChildEntityId(current.getId());
            for (EntityComposition composition : compositions) {
                queue.add(composition.getParentEntity());
                relatedEntities.add(composition.getParentEntity());
            }
        }
        return relatedEntities.stream()
                .map(e -> exampleWordRepository.findByEntityId(e.getId()))
                .flatMap(List::stream)
                .map(ExampleWord::getExample)
                .map(Example::getSource)
                .distinct()
                .toList();
    }
}

