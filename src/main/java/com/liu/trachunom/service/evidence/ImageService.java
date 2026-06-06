package com.liu.trachunom.service.evidence;
import com.liu.trachunom.entity.evidence.Image;
import com.liu.trachunom.repository.ImageRepository;
import com.vaadin.hilla.crud.ListRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import java.util.List;
@Service
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
public class ImageService extends ListRepositoryService<Image, Long, ImageRepository> {
    private final ImageRepository imageRepository;
    public Image findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
    public List<Image> findAll() {
        return imageRepository.findAll();
    }
    @Transactional
    public Image save(Image image) {
        return imageRepository.save(image);
    }
    @Transactional
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }
    public boolean existsById(Long id) {
        return imageRepository.existsById(id);
    }
}
