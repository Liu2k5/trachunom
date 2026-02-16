package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.ExampleWordDto;
import com.liu.trachunom.entity.ExampleWord;
import com.liu.trachunom.entity.ExampleWordId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.EntityService;
import com.liu.trachunom.service.ExampleService;
import com.liu.trachunom.service.ExampleWordService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Endpoint
@AnonymousAllowed
@RequiredArgsConstructor
public class ExampleWordEndpoint {
    private final ExampleWordService exampleWordService;
    private final EntityMapper entityMapper;
    private final ExampleService exampleService;
    private final EntityService entityService;

    public List<ExampleWord> findAll() {
        return exampleWordService.findAll();
    }

    public List<ExampleWord> findByExampleId(Long exampleId) {
        return exampleWordService.findByExampleId(exampleId);
    }

    public ExampleWordDto save(Long exampleId, Long entityId, Long position) {
        System.out.println(exampleId + " " + entityId + " " + position);
        ExampleWord exampleWord = ExampleWord.builder()
                .exampleWordId(ExampleWordId.builder().exampleId(exampleId).entityId(entityId).position(position).build())
                .example(exampleService.findById(exampleId))
                .entity(entityService.findById(entityId))
                .build();
        exampleWordService.save(exampleWord);
        return entityMapper.toExampleWordDto(exampleWord);
    }

    public void delete(Long exampleId, Long entityId, Long position) {
        ExampleWordId id = new ExampleWordId();
        id.setExampleId(exampleId);
        id.setEntityId(entityId);
        id.setPosition(position);
        exampleWordService.deleteById(id);
    }
}
