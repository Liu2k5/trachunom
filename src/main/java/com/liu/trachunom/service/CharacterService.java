package com.liu.trachunom.service;

import com.liu.trachunom.entity.EntityX;
import com.liu.trachunom.repository.EntityRepository;
import org.springframework.stereotype.Service;

import com.liu.trachunom.dto.CharacterDto;
import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final RadicalService radicalService;
    private final EntityRepository entityRepository;

    public CharacterX findByUnicode(Integer unicode) {
        return characterRepository.findByUnicode(unicode).orElse(null);
    }

    public List<CharacterX> findAll() {
        return characterRepository.findAll();
    }

    public CharacterDto toDto(CharacterX character) {
        if (character == null) {
            return null;
        }
        return CharacterDto.builder()
                .character(Character.toString(character.getUnicode()))
                .radicalId(character.getRadical() != null ? character.getRadical().getId() : null)
                .additionalStrokeNumber(character.getAdditionalStrokeNumber())
                .totalStrokeNumber(character.getTotalStrokeNumber())
                .build();
    }

    public CharacterX parse(CharacterDto characterDto) {
        if (characterDto == null || characterDto.getCharacter() == null) {
            return null;
        }
        CharacterX character = findByUnicode(characterDto.getCharacter().codePointAt(0));
        if (character == null) {
            throw new RuntimeException("Kí tự không tồn tại trong cơ sở dữ liệu");
        }
        return CharacterX.builder()
                .unicode(characterDto.getCharacter().codePointAt(0))
                .radical(characterDto.getRadicalId() != null ? radicalService.findById(characterDto.getRadicalId()) : null)
                .additionalStrokeNumber(characterDto.getAdditionalStrokeNumber())
                .totalStrokeNumber(characterDto.getTotalStrokeNumber())
                .build();
    }

    @Transactional
    public void save(CharacterDto characterDto) {
        CharacterX character = parse(characterDto);
        save(character);
    }

    public void save(CharacterX character) {
        characterRepository.save(character);
    }

    @Transactional
    public void delete(Integer unicode) {
        characterRepository.deleteById(unicode);
    }

    public EntityX findById(Long id) {
        return entityRepository.findById(id).orElse(null);
    }

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
