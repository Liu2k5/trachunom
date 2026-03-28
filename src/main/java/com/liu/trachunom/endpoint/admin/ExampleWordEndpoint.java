package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.ExampleWordDto;
import com.liu.trachunom.entity.example.ExampleWord;
import com.liu.trachunom.entity.example.ExampleWordId;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.entity.EntityService;
import com.liu.trachunom.service.example.ExampleService;
import com.liu.trachunom.service.example.ExampleWordService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.Endpoint;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
@RolesAllowed("ADMIN")
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
        ExampleWord exampleWord = ExampleWord.builder()
                .exampleWordId(ExampleWordId.builder().exampleId(exampleId).entityId(entityId).position(position).build())
                .example(exampleService.findById(exampleId))
                .entity(entityService.findById(entityId))
                .build();
        exampleWordService.save(exampleWord);
        return entityMapper.toExampleWordDto(exampleWord);
    }

    public void deleteByEachId(Long exampleId, Long entityId, Long position) {
        ExampleWordId id = ExampleWordId.builder().exampleId(exampleId).entityId(entityId).position(position).build();
        delete(id);
    }

    public void delete(ExampleWordId id) {
        exampleWordService.deleteById(id);
    }
}
