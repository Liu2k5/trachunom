package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.ExampleDto;
import com.liu.trachunom.entity.example.Example;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.example.ExampleService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.Endpoint;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Endpoint
@RolesAllowed("ADMIN")
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
        return exampleService.getHnomStringByExampleId(example.getId());
    }

    public String getQnguString(Example example) {
        return exampleService.getQnguStringByExampleId(example.getId());
    }
}
