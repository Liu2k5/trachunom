package com.liu.trachunom.service.evidence;

import com.liu.trachunom.entity.evidence.Style;
import com.liu.trachunom.repository.StyleRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class StyleService extends ListRepositoryService<Style, Long, StyleRepository> {
    private final StyleRepository styleRepository;

    public Style findById(Long id) {
        return styleRepository.findById(id).orElse(null);
    }

    public List<Style> findAll() {
        return styleRepository.findAll();
    }

    @Transactional
    public Style save(Style style) {
        return styleRepository.save(style);
    }

    @Transactional
    public void delete(Long id) {
        styleRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return styleRepository.existsById(id);
    }

    public boolean existsByDescription(String string) {
        return styleRepository.existsByDescription(string);
    }

    public void deleteById(Long id) {
        styleRepository.deleteById(id);
    }
}
