package com.liu.trachunom.service;

import com.liu.trachunom.entity.Pronunciation;
import com.liu.trachunom.entity.PronunciationChange;
import com.liu.trachunom.entity.PronunciationClassification;
import com.liu.trachunom.repository.PronunciationClassificationRepository;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PronunciationClassificationService {
    private final PronunciationClassificationRepository pronunciationClassificationRepository;
//    private final VisualTool visualTool;

    public PronunciationClassification getChangingPronunciation() {
        return pronunciationClassificationRepository.findByDescription("Biến âm");
    }

    public PronunciationClassification getBorrowingPronunciation() {
        return pronunciationClassificationRepository.findByDescription("Mượn âm");
    }

    boolean isChangingPronunciation(PronunciationClassification pronunciationClassification) {
        if (pronunciationClassification == null || pronunciationClassification.getDescription() == null) {
            return false;
        }
        if (pronunciationClassification.getDescription().equals("Biến âm")) {
            return true;
        } else {
            return false;
        }
    }

    boolean isBorrowingPronunciation(PronunciationClassification pronunciationClassification) {
        if (pronunciationClassification == null || pronunciationClassification.getDescription() == null) {
            return false;
        }
        if (pronunciationClassification.getDescription().equals("Mượn âm")) {
            return true;
        } else {
            return false;
        }
    }

    public List<PronunciationClassification> findAll() {
        return pronunciationClassificationRepository.findAll();
    }

}
