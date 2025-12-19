package com.liu.trachunom.service;

import com.liu.trachunom.entity.*;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisualTool {
    private final StructureClassificationService structureClassificationService;
    private final PronunciationClassificationService pronunciationClassificationService;

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

            StructureClassification structureClassification = subStructure.getStructureClassification();
            container.getStyle().setBackgroundColor(
                structureClassificationService.isPhoneticClassification(structureClassification) ? "blue" :
                structureClassificationService.isNoneClassification(structureClassification) ? "grey" :
                structureClassificationService.isIdeographicClassification(structureClassification) ? "red" : "white");
            container.getStyle().setWidth("100%")
                    .setMargin("0px")
                    .setPadding("5px 0px 5px")
                    .setDisplay(Style.Display.FLEX)
                    .setJustifyContent(Style.JustifyContent.CENTER)
                    .setAlignItems(Style.AlignItems.CENTER);
            vLayout.setPadding(false);
            vLayout.setMargin(false);
            vLayout.getStyle().setWidth("100%");
            vLayout.getStyle().setGap("0px");

            HorizontalLayout hLayout = drawStructure(subStructure.getSubStructure().getSubStructures(), depth + 1);
            vLayout.add(container, hLayout);
            if (i > 0) {
                HorizontalLayout spacer = new HorizontalLayout();
                spacer.getStyle().setWidth("10px").setFlexGrow("0");
                layout.add(spacer);
            }
            layout.add(vLayout);
        }
        layout.setPadding(false);
        layout.setMargin(false);
        layout.getStyle().setWidth("100%");
        layout.getStyle().setGap("0px");
        return layout;
    }

    public VerticalLayout drawPronunciation(List<PronunciationChange> pronunciationChanges) {
        try {
            return drawPronunciation(pronunciationChanges, 0);
        } catch (StackOverflowError e) {
            return new VerticalLayout(new H5("Vòng lặp vô hạn"));
        } catch (Exception e) {
            return new VerticalLayout(new H5("Lỗi"));
        }
    }

    public VerticalLayout drawPronunciation(List<PronunciationChange> pronunciationChanges, int depth) throws StackOverflowError {
        if (depth > MAX_DEPTH) {
            throw new StackOverflowError();
        }

        if (pronunciationChanges == null || pronunciationChanges.isEmpty()) {
            return new VerticalLayout();
        }

        VerticalLayout layout = new VerticalLayout();
        for (PronunciationChange pronunciationChange : pronunciationChanges) {
            PronunciationClassification pronunciationClassification = pronunciationChange.getPronunciationClassification();

            HorizontalLayout hLayout = new HorizontalLayout();
            H3 arrow = new H3("<-");
            arrow.getStyle().setColor(
                    pronunciationClassificationService.isChangingPronunciation(pronunciationClassification) ? "red" :
                    pronunciationClassificationService.isBorrowingPronunciation(pronunciationClassification) ? "blue" : "black");

            Paragraph content = new Paragraph(pronunciationChange.getPreviousPronunciation().getQuocNgu().getDescription());

            HorizontalLayout container = new HorizontalLayout();
            container.setPadding(false);
            container.setMargin(false);
            container.add(arrow, content);
            container.getStyle().setWidth("100%")
                    .setMargin("0px")
                    .setPadding("5px 0px 5px")
                    .setDisplay(Style.Display.FLEX)
                    .setJustifyContent(Style.JustifyContent.CENTER)
                    .setAlignItems(Style.AlignItems.CENTER);

            VerticalLayout vLayout = drawPronunciation(pronunciationChange.getPreviousPronunciation().getPronunciationChanges(), depth + 1);
            hLayout.add(container, vLayout);
            layout.add(hLayout);

        }
        layout.setPadding(false);
        layout.setMargin(false);
        layout.getStyle().setWidth("100%");
        layout.getStyle().setGap("0px");
        return layout;
    }

}
