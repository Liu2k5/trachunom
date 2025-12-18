package com.liu.trachunom.service;

import com.liu.trachunom.entity.Source;
import com.liu.trachunom.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceService {
    private final SourceRepository sourceRepository;

    public Source findById(Long id) {
        return sourceRepository.findById(id).orElse(null);
    }

    public List<Source> findAll() {
        return sourceRepository.findAll();
    }

    @Transactional
    public Source save(Source source) {
        return sourceRepository.save(source);
    }

    @Transactional
    public void delete(Long id) {
        sourceRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return sourceRepository.existsById(id);
    }

    public void deleteById(Long id) {
        sourceRepository.deleteById(id);
    }

    public boolean existsByName(String string) {
        return sourceRepository.existsByName(string);
    }
}

