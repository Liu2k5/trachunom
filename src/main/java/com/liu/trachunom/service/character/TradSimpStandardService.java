package com.liu.trachunom.service.character;

import com.liu.trachunom.entity.character.TradSimpStandard;
import com.liu.trachunom.entity.character.TradSimpStandardId;
import com.liu.trachunom.repository.TradSimpStandardRepository;
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
public class TradSimpStandardService extends ListRepositoryService<TradSimpStandard, TradSimpStandardId, TradSimpStandardRepository> {
    private final TradSimpStandardRepository tradSimpStandardRepository;

    public TradSimpStandard findById(TradSimpStandardId id) {
        return tradSimpStandardRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(TradSimpStandard tradSimpStandard) {
        tradSimpStandardRepository.save(tradSimpStandard);
    }

    @Transactional
    public void delete(TradSimpStandardId id) {
        tradSimpStandardRepository.deleteById(id);
    }

    public boolean existsById(TradSimpStandardId id) {
        return tradSimpStandardRepository.existsById(id);
    }

    public List<TradSimpStandard> findAll() {
        return tradSimpStandardRepository.findAll();
    }

    public void deleteById(TradSimpStandardId tradSimpStandardId) {
        tradSimpStandardRepository.deleteById(tradSimpStandardId);
    }
}
