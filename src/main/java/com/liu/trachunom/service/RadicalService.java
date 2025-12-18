package com.liu.trachunom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liu.trachunom.entity.Radical;
import com.liu.trachunom.repository.RadicalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RadicalService {
    private final RadicalRepository radicalRepository;

    public List<Radical> findAll() {
        List<Radical> radicals = radicalRepository.findAll();
        if (radicals.isEmpty()) {
            return radicals;
        }
        return radicals.stream()
        .sorted((o1, o2) -> {
            try {
                int number1 = Integer.parseInt(o1.getId().split("[^0-9]")[0]);
                int number2 = Integer.parseInt(o2.getId().split("[^0-9]")[0]);
                if (number1 != number2) {
                    return number1 - number2;
                } else {
                    return o1.getId().compareTo(o2.getId());
                }
            } catch (Exception e) {
                return 0;
            }
        })
        .toList();
    }

    public List<String> findAllRadicalStrings() {
        return radicalRepository.findAll().stream()
                .sorted((o1, o2) -> {
            int number1 = Integer.parseInt(o1.getId().split("[^0-9]")[0]);
            int number2 = Integer.parseInt(o2.getId().split("[^0-9]")[0]);
            if (number1 != number2) {
                return number1 - number2;
            } else {
                return o1.getId().compareTo(o2.getId());
            }
        })
        .map(radical -> new String(Character.toChars(radical.getUnicode()))).toList();
    }

    public Radical findById(String id) {
        return radicalRepository.findById(id).orElseThrow(() -> new RuntimeException("Bộ thủ không tồn tại"));
    }

    public void save(Radical radical) {
        radicalRepository.save(radical);
    }

    public void deleteById(String id) {
        radicalRepository.deleteById(id);
    }

    public boolean existsById(String string) {
        return radicalRepository.existsById(string);
    }

    public Radical findByUnicode(int i) {
        return radicalRepository.findByUnicode(i);
    }
}
