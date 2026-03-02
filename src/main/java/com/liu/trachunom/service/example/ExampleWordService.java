package com.liu.trachunom.service.example;

import com.liu.trachunom.entity.example.ExampleWord;
import com.liu.trachunom.entity.example.ExampleWordId;
import com.liu.trachunom.repository.ExampleWordRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class ExampleWordService extends ListRepositoryService<ExampleWord, ExampleWordId, ExampleWordRepository> {
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

    public List<ExampleWord> findByExampleId(Long exampleId) {
        return exampleWordRepository.findByExample_IdOrderByExampleWordId_Position(exampleId);
    }
}

