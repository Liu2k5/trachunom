package com.liu.trachunom.service;

import com.liu.trachunom.entity.SubStructure;
import com.liu.trachunom.repository.ClassificationRepository;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisualTool {
    private final ClassificationService classificationService;

    private final int MAX_DEPTH = 50;

    public HorizontalLayout drawStructure(List<SubStructure> subStructures) {
        try {
            return drawStructure(subStructures, 0);
        } catch (StackOverflowError e) {
            return new HorizontalLayout(new H5("Vòng lặp vô hạn"));
        } catch (Exception e) {
            return new HorizontalLayout(new H5("Lỗi"));
        }
    }

    public HorizontalLayout drawStructure(List<SubStructure> subStructures, int depth) throws StackOverflowError {
        if (depth > MAX_DEPTH) {
            throw new StackOverflowError();
        }

        if (subStructures == null || subStructures.isEmpty()) {
            return new HorizontalLayout();
        }

        HorizontalLayout layout = new HorizontalLayout();
        for (int i = 0; i < subStructures.size(); i++) {
            VerticalLayout vLayout = new VerticalLayout();
            SubStructure subStructure = subStructures.get(i);
            Integer quantity = subStructure.getQuantity();
            H5 content = new H5(subStructure.getSubStructure().getCharacter().getString() + (quantity > 1 ? " x" + quantity : ""));
            content.getStyle().setColor("white");

            VerticalLayout container = new VerticalLayout();
            container.setPadding(false);
            container.setMargin(false);
            container.add(content);

            container.getStyle().setBackgroundColor(
                    classificationService.isPhoneticClassification(subStructure.getClassification()) ? "blue" :
                            classificationService.isNoneClassification(subStructure.getClassification()) ? "grey" :
                                    classificationService.isIdeographicClassification(subStructure.getClassification()) ? "red" : "white");
            container.getStyle().setWidth("100%");
            container.getStyle().setMargin("0px");
            container.getStyle().setDisplay(Style.Display.FLEX);
            container.getStyle().setJustifyContent(Style.JustifyContent.CENTER);
            container.getStyle().setAlignItems(Style.AlignItems.CENTER);
            vLayout.setPadding(false);
            vLayout.setMargin(false);
            vLayout.getStyle().setWidth("100%");
            vLayout.getStyle().setGap("0px");
//            vLayout.getStyle().setBorder("1px solid unset");
//            vLayout.getStyle().setBoxSizing(Style.BoxSizing.CONTENT_BOX);
            HorizontalLayout hLayout = drawStructure(subStructure.getSubStructure().getSubStructures(), depth + 1);
            vLayout.add(container, hLayout);
            layout.add(vLayout);
        }
        layout.setPadding(false);
        layout.setMargin(false);
        layout.getStyle().setWidth("100%");
        layout.getStyle().setGap("0px");
        return layout;
    }
}
