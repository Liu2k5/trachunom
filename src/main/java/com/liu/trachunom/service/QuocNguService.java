package com.liu.trachunom.service;

import java.util.List;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.QuocNgu;
import com.liu.trachunom.repository.QuocNguRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class QuocNguService extends ListRepositoryService<QuocNgu, Long, QuocNguRepository> {
    private final QuocNguRepository quocNguRepository;
    
    public List<String> findAllPronunciations() {
        return quocNguRepository.findAll().stream()
                .map(QuocNgu::getDescription)
                .toList();
    }

    public List<QuocNgu> findAll() {
        return quocNguRepository.findAll();
    }

    public QuocNgu findByDescription(String description) {
        return quocNguRepository.findByDescription(description).stream().findFirst().orElse(null);
    }

    public boolean existsByDescription(String description) {
        return quocNguRepository.existsByDescription(description);
    }

    public QuocNgu findById(Long id) {
        return quocNguRepository.findById(id).orElse(null);
    }

    @Transactional
    public QuocNgu save(QuocNgu quocNgu) {
        return quocNguRepository.save(quocNgu);
    }

    @Transactional
    public void delete(Long id) {
        quocNguRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return quocNguRepository.existsById(id);
    }

    public void deleteById(Long id) { quocNguRepository.deleteById(id); }
}
