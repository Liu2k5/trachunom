package com.liu.trachunom.endpoint;

import com.liu.trachunom.dto.ExampleDto;
import com.liu.trachunom.entity.Example;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.ExampleService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Endpoint
@AnonymousAllowed
@RequiredArgsConstructor
public class ExampleEndpoint {
    private final ExampleService exampleService;
    private final EntityMapper entityMapper;

    public List<Example> findAll() {
        return exampleService.findAll();
    }

    public Example save(ExampleDto exampleDto) {
        Example example = entityMapper.toExample(exampleDto);
        exampleService.save(example);
        return example;
    }

    public void delete(Long id) {
        exampleService.deleteById(id);
    }

    public String getHnomString(Example example) {
        return exampleService.getHnomString(example);
    }

    public String getQnguString(Example example) {
        return exampleService.getQnguString(example);
    }
}
