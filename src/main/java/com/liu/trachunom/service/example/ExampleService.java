package com.liu.trachunom.service.example;

import com.liu.trachunom.entity.entity.EntityComposition;
import com.liu.trachunom.entity.entity.EntityX;
import com.liu.trachunom.entity.example.Example;
import com.liu.trachunom.entity.example.ExampleWord;
import com.liu.trachunom.repository.EntityCompositionRepository;
import com.liu.trachunom.repository.ExampleRepository;
import com.liu.trachunom.repository.ExampleWordRepository;
import com.liu.trachunom.service.entity.EntityService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class ExampleService extends ListRepositoryService<Example, Long, ExampleRepository> {
    private final ExampleRepository exampleRepository;
    private final ExampleWordRepository exampleWordRepository;
    private final EntityService entityService;
    private final EntityCompositionRepository entityCompositionRepository;

    public List<Example> findAll() {
        return  exampleRepository.findAll();
    }

    public void save(Example newExample) {
        exampleRepository.save(newExample);
    }

    public void deleteById(Long id) {
        exampleRepository.deleteById(id);
    }

    public String getHnomStringByExampleId(Long exampleId) {
        List<ExampleWord> foundExampleWords = exampleWordRepository.findByExample_IdOrderByExampleWordId_Position(exampleId);
        StringBuilder output = new StringBuilder();
        for (ExampleWord exampleWord : foundExampleWords) {
            output.append(entityService.getHnomStringById(exampleWord.getEntity().getId()));
        }
        return output.toString();
    }

    public String getQnguStringByExampleId(Long exampleId) {
        List<ExampleWord> foundExampleWords = exampleWordRepository.findByExample_IdOrderByExampleWordId_Position(exampleId);
        StringBuilder output = new StringBuilder();
        if (!foundExampleWords.isEmpty()) {
            for (int i = 0; i < foundExampleWords.size(); i++) {
                if (i > 0) {
                    output.append(" ");
                }
                output.append(entityService.getQnguStringById(foundExampleWords.get(i).getEntity().getId()));
            }
        }
        return output.toString();
    }

    public List<Example> findByEntityId(Long id) {
        EntityX entity = entityService.findById(id);
        if (entity == null) {
            return List.of();
        }
        List<EntityX> relatedEntities = new ArrayList<>();
        relatedEntities.add(entity);
        relatedEntities.addAll(
                entityCompositionRepository.findByChildEntityId(entity.getId())
                        .stream()
                        .map(EntityComposition::getParentEntity)
                        .toList()
        );

        List<ExampleWord> foundExampleWords = relatedEntities.stream()
                .map(e -> exampleWordRepository.findByEntity_Id(e.getId()))
                .flatMap(List::stream)
                .toList();
        return foundExampleWords.stream()
                .map(ExampleWord::getExample)
                .distinct()
                .toList();
    }

    public Example findById(Long exampleId) {
        return exampleRepository.findById(exampleId).orElse(null);
    }
}
