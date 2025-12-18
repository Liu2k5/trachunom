package com.liu.trachunom.service;

import com.liu.trachunom.entity.Etymology;
import com.liu.trachunom.repository.EtymologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtymologyService {
    private final EtymologyRepository etymologyRepository;

    public List<Etymology> findAll() {
        return etymologyRepository.findAll();
    }
}