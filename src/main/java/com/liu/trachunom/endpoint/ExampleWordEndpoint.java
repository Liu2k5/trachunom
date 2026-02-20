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
import java.util.stream.Collectors;

@Endpoint
@AnonymousAllowed
@RequiredArgsConstructor
public class ExampleWordEndpoint {
    private final ExampleWordService exampleWordService;
    private final EntityMapper entityMapper;
    private final ExampleService exampleService;
    private final EntityService entityService;

    public List<ExampleWordDto> list() {

        return exampleWordService.findAll().stream()
                .map(entityMapper::toExampleWordDto)
                .collect(Collectors.toList());
    }

    public List<ExampleWordDto> findByExampleId(Long exampleId) {
        return exampleWordService.findByExampleId(exampleId).stream()
                .map(entityMapper::toExampleWordDto)
                .collect(Collectors.toList());
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

    public void deleteByEachId(Long exampleId, Long entityId, Long position) {
        ExampleWordId id = new ExampleWordId();
        id.setExampleId(exampleId);
        id.setEntityId(entityId);
        id.setPosition(position);
        delete(id);
    }

    public void delete(ExampleWordId id) {
        exampleWordService.deleteById(id);
    }
}
