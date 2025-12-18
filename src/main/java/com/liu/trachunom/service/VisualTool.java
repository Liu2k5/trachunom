package com.liu.trachunom.service;

import com.liu.trachunom.entity.SubStructure;
import com.liu.trachunom.repository.ClassificationRepository;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisualTool {
    private final ClassificationService classificationService;

    public HorizontalLayout drawStructure(List<SubStructure> subStructures) {
        if (subStructures == null || subStructures.isEmpty()) {
            return new HorizontalLayout();
        }

        HorizontalLayout layout = new HorizontalLayout();
        for (int i = 0; i < subStructures.size(); i++) {
            VerticalLayout vLayout = new VerticalLayout();
            SubStructure subStructure = subStructures.get(i);
            Integer quantity = subStructure.getQuantity();
            H5 content = new H5(subStructure.getSubStructure().getCharacter().getString() + (quantity > 1 ? " x" + quantity : ""));

            content.getStyle().setBackgroundColor(
                    classificationService.isPhoneticClassification(subStructure.getClassification()) ? "blue" :
                    classificationService.isNoneClassification(subStructure.getClassification()) ? "grey" :
                    classificationService.isIdeographicClassification(subStructure.getClassification()) ? "red" : "white");
            content.getStyle().setColor("white");
            content.getStyle().setPadding("5px");
            content.getStyle().setBorderRadius("5px");

            HorizontalLayout hLayout = drawStructure(subStructure.getSubStructure().getSubStructures());
            vLayout.add(content, hLayout);
        }
        return layout;
    }
}
