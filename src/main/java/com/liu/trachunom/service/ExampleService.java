package com.liu.trachunom.service;

import com.liu.trachunom.entity.Example;
import com.liu.trachunom.entity.ExampleWord;
import com.liu.trachunom.repository.ExampleRepository;
import com.liu.trachunom.repository.ExampleWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleRepository exampleRepository;
    private final ExampleWordRepository exampleWordRepository;
    private final EntityService entityService;

    public List<Example> findAll() {
        return  exampleRepository.findAll();
    }

    public void save(Example newExample) {
        exampleRepository.save(newExample);
    }

    public void deleteById(Long id) {
        exampleRepository.deleteById(id);
    }

    public String getHnomByExample(Example example) {
        List<ExampleWord> foundExampleWords = exampleWordRepository.findByExample_IdOrderByExampleWordId_Position(example.getId());
        StringBuilder output = new StringBuilder();
        for (ExampleWord exampleWord : foundExampleWords) {
            output.append(entityService.getHnomString(exampleWord.getEntity()));
        }
        return output.toString();
    }

    public String getQnguByExample(Example example) {
        List<ExampleWord> foundExampleWords = exampleWordRepository.findByExample_IdOrderByExampleWordId_Position(example.getId());
        StringBuilder output = new StringBuilder();
        if (!foundExampleWords.isEmpty()) {
            for (int i = 0; i < foundExampleWords.size(); i++) {
                if (i > 0) {
                    output.append(" ");
                }
                output.append(entityService.getQnguString(foundExampleWords.get(i).getEntity()));
            }
        }
        return output.toString();
    }

    public List<Example> findByEntityId(Long id) {
        List<ExampleWord> foundExampleWords = exampleWordRepository.findByEntity_Id(id);
        return foundExampleWords.stream()
                .map(ExampleWord::getExample)
                .distinct()
                .toList();
    }
}
