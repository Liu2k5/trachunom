package com.liu.trachunom.service;

import com.liu.trachunom.entity.EntityX;
import com.liu.trachunom.repository.EntityRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.crud.ListRepositoryService;
import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class CharacterService extends ListRepositoryService<CharacterX, Integer, CharacterRepository> {
    private final CharacterRepository characterRepository;
    private final RadicalService radicalService;
    private final EntityRepository entityRepository;

    public CharacterX findByUnicode(Integer unicode) {
        return characterRepository.findByUnicode(unicode).orElse(null);
    }

    public List<CharacterX> findAll() {
        return characterRepository.findAll();
    }

    public void save(CharacterX character) {
        characterRepository.save(character);
    }

    @Transactional
    public void delete(Integer unicode) {
        characterRepository.deleteById(unicode);
    }
//
//    public EntityX findById(Long id) {
//        return entityRepository.findById(id).orElse(null);
//    }

    public boolean existsByUnicode(Integer unicode) {
        return characterRepository.existsById(unicode);
    }

    public void deleteById(Integer unicode) {
        characterRepository.deleteById(unicode);
    }

    public void deleteByUnicode(Integer unicode) {
        characterRepository.deleteById(unicode);
    }
}
