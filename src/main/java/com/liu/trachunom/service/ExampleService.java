package com.liu.trachunom.service;

import com.liu.trachunom.entity.Example;
import com.liu.trachunom.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public List<Example> findAll() {
        return  exampleRepository.findAll();
    }


    public void save(Example newExample) {
        exampleRepository.save(newExample);
    }

    public void deleteById(Long id) {
        exampleRepository.deleteById(id);
    }
}
