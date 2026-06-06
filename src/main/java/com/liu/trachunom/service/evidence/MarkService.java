package com.liu.trachunom.service.evidence;
import com.liu.trachunom.entity.evidence.Mark;
import com.liu.trachunom.entity.evidence.MarkId;
import com.liu.trachunom.repository.MarkRepository;
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
public class MarkService extends ListRepositoryService<Mark, MarkId, MarkRepository> {
    private final MarkRepository markRepository;
    public Mark findById(MarkId id) {
        return markRepository.findById(id).orElse(null);
    }
    public List<Mark> findAll() {
        return markRepository.findAll();
    }
    @Transactional
    public Mark save(Mark mark) {
        return markRepository.save(mark);
    }
    @Transactional
    public void deleteById(MarkId id) {
        markRepository.deleteById(id);
    }
    public boolean existsById(MarkId id) {
        return markRepository.existsById(id);
    }
    public List<Mark> findByImageId(Long imageId) { return markRepository.findByImage_Id(imageId); }

    public List<Mark> findByEntityId(Long entityId) { return markRepository.findByEntity_Id(entityId);
    }
}
