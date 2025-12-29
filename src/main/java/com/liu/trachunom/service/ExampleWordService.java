package com.liu.trachunom.service;

import com.liu.trachunom.entity.Example;
import com.liu.trachunom.entity.ExampleWord;
import com.liu.trachunom.entity.ExampleWordId;
import com.liu.trachunom.repository.ExampleWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExampleWordService {
    private final ExampleWordRepository exampleWordRepository;

    public List<ExampleWord> findAll() {
        return exampleWordRepository.findAll();
    }

    public List<ExampleWord> findByExampleIdOrderByPosition(Long exampleId) {
        return exampleWordRepository.findByExample_IdOrderByExampleWordId_Position(exampleId);
    }

    public Optional<ExampleWord> findById(ExampleWordId id) {
        return exampleWordRepository.findById(id);
    }

    @Transactional
    public ExampleWord save(ExampleWord exampleWord) {
        return exampleWordRepository.save(exampleWord);
    }

    @Transactional
    public void deleteById(ExampleWordId id) {
        exampleWordRepository.deleteById(id);
    }

    public List<ExampleWord> findByExample(Example example) {
        return exampleWordRepository.findByExample_IdOrderByExampleWordId_Position(example.getId());
    }
}

