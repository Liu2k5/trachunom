package com.liu.trachunom.endpoint.admin;

import com.liu.trachunom.dto.ImageDto;
import com.liu.trachunom.entity.evidence.Image;
import com.liu.trachunom.mapper.EntityMapper;
import com.liu.trachunom.service.evidence.ImageService;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@BrowserCallable
@RolesAllowed("ADMIN")
@RequiredArgsConstructor
public class ImageEndpoint {
    private final EntityMapper entityMapper;
    private final ImageService imageService;

    public ImageDto save(ImageDto imageDto) {
        Image image = entityMapper.toImage(imageDto);
        return entityMapper.toImageDto(imageService.save(image));
    }

    public void delete(Long id) {
        imageService.delete(id);
    }

    public List<ImageDto> findAll() {
        return imageService.findAll().stream()
                .map(entityMapper::toImageDto)
                .collect(Collectors.toList());
    }
}

