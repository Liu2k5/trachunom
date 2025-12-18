package com.liu.trachunom.service;

import com.liu.trachunom.entity.Example;
import com.liu.trachunom.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public Example findById(Long id) {
        return exampleRepository.findById(id).orElse(null);
    }

    public List<Example> findAll() {
        return exampleRepository.findAll();
    }

    @Transactional
    public Example save(Example example) {
        return exampleRepository.save(example);
    }

    @Transactional
    public void delete(Long id) {
        exampleRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return exampleRepository.existsById(id);
    }
}

