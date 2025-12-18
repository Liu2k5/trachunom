package com.liu.trachunom.service;

import com.liu.trachunom.entity.Picture;
import com.liu.trachunom.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;

    public Picture findById(Long id) {
        return pictureRepository.findById(id).orElse(null);
    }

    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Transactional
    public Picture save(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Transactional
    public void delete(Long id) {
        pictureRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return pictureRepository.existsById(id);
    }
}

