package com.liu.trachunom.service;

import com.liu.trachunom.entity.*;
import com.liu.trachunom.view.EntityDetailView;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.RouterLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisualTool {
    private final StructureClassificationService structureClassificationService;
    private final PronunciationClassificationService pronunciationClassificationService;
    private final PronunciationEvolutionService pronunciationEvolutionService;
    private final EntityEvolutionService entityEvolutionService;

    private final int MAX_DEPTH = 50;

    public HorizontalLayout drawStructure(Structure structure) {
        return drawStructure(structure.getStructureComponents());
    }

    public HorizontalLayout drawStructure(List<StructureComponent> structureComponents) {
        try {
            HorizontalLayout layout = drawStructure(structureComponents, 0);
            if (layout.getChildren().count() == 0) {
                return new HorizontalLayout(new H5("Chữ tượng hình hoặc cấu tạo chưa xác định"));
            }
            return layout;
        } catch (StackOverflowError e) {
            return new HorizontalLayout(new H5("Vòng lặp vô hạn"));
        } catch (Exception e) {
            return new HorizontalLayout(new H5("Lỗi"));
        }
    }

    public HorizontalLayout drawStructure(List<StructureComponent> structureComponents, int depth) throws StackOverflowError {
        if (depth > MAX_DEPTH) {
            throw new StackOverflowError();
        }

        if (structureComponents == null || structureComponents.isEmpty()) {
            return new HorizontalLayout();
        }

        HorizontalLayout layout = new HorizontalLayout();
        for (int i = 0; i < structureComponents.size(); i++) {
            VerticalLayout vLayout = new VerticalLayout();
            StructureComponent structureComponent = structureComponents.get(i);
            Integer quantity = structureComponent.getQuantity();
            H5 content = new H5(structureComponent.getStructureComponent().getCharacter().getString() + (quantity > 1 ? " x" + quantity : ""));
            content.getStyle().setColor("white");

            VerticalLayout container = new VerticalLayout();
            container.setPadding(false);
            container.setMargin(false);
            container.add(content);

            StructureClassification structureClassification = structureComponent.getStructureClassification();
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

            HorizontalLayout hLayout = drawStructure(structureComponent.getStructureComponent().getStructureComponents(), depth + 1);
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

    public VerticalLayout drawPronunciation(Pronunciation pronunciation) {
        List<PronunciationEvolution> pronunciationEvolutions = pronunciationEvolutionService.findByToPronunciation(pronunciation);
        return drawPronunciation(pronunciationEvolutions);
    }

    public VerticalLayout drawPronunciation(List<PronunciationEvolution> pronunciationEvolutions) {
        try {
            return drawPronunciation(pronunciationEvolutions, 0);
        } catch (StackOverflowError e) {
            return new VerticalLayout(new H5("Vòng lặp vô hạn"));
        } catch (Exception e) {
            return new VerticalLayout(new H5("Lỗi"));
        }
    }

    public VerticalLayout drawPronunciation(List<PronunciationEvolution> pronunciationEvolutions, int depth) throws StackOverflowError {
        if (depth > MAX_DEPTH) {
            throw new StackOverflowError();
        }

        if (pronunciationEvolutions == null || pronunciationEvolutions.isEmpty()) {
            return new VerticalLayout();
        }

        VerticalLayout layout = new VerticalLayout();
        for (PronunciationEvolution pronunciationEvolution : pronunciationEvolutions) {
            PronunciationClassification pronunciationClassification = pronunciationEvolution.getPronunciationClassification();

            HorizontalLayout hLayout = new HorizontalLayout();
//            H3 arrow = new H3("→");
            H3 arrow = new H3("<-");
            arrow.getStyle().setWidth("10px");
            arrow.getStyle().setColor(
                    pronunciationClassificationService.isChangingPronunciation(pronunciationClassification) ? "red" :
                    pronunciationClassificationService.isBorrowingPronunciation(pronunciationClassification) ? "blue" : "black");

//            Paragraph content = new Paragraph(pronunciationEvolution.getToPronunciation().getQuocNgu().getDescription());
            Paragraph content = new Paragraph(pronunciationEvolution.getFromPronunciation().getQuocNgu().getDescription());

            HorizontalLayout container = new HorizontalLayout();
            container.setPadding(false);
            container.setMargin(false);
            container.add(arrow, content);
            container.getStyle().setWidth("100%")
                    .setMargin("0px")
                    .setPadding("5px 0px 5px")
                    .setDisplay(Style.Display.FLEX)
                    .setJustifyContent(Style.JustifyContent.CENTER)
                    .setAlignItems(Style.AlignItems.CENTER)
                    .setHeight("20px");

//            VerticalLayout vLayout = drawPronunciation(pronunciationEvolutionService.findByFromPronunciation(pronunciationEvolution.getToPronunciation()), depth + 1);
            VerticalLayout vLayout = drawPronunciation(pronunciationEvolutionService.findByToPronunciation(pronunciationEvolution.getFromPronunciation()), depth + 1);
            hLayout.add(container, vLayout);
            layout.add(hLayout);

        }
        layout.setPadding(false);
        layout.setMargin(false);
        layout.getStyle().setWidth("100%");
        layout.getStyle().setGap("0px");
        return layout;
    }

    public VerticalLayout drawEntityEvolution(EntityX entity) {
        try {
            return drawEntityEvolution(entity, 0);
        } catch (StackOverflowError e) {
            return new VerticalLayout(new H5("Vòng lặp vô hạn"));
        } catch (Exception e) {
            return new VerticalLayout(new H5("Lỗi"));
        }
    }

    public VerticalLayout drawEntityEvolution(EntityX entity, int depth) throws StackOverflowError {
        if (depth > MAX_DEPTH) {
            throw new StackOverflowError();
        }

        if (entity == null) {
            return new VerticalLayout();
        }

        List<EntityEvolution> entityEvolutions = entityEvolutionService.findByToEntityId(entity.getId());

        VerticalLayout layout = new VerticalLayout();

        VerticalLayout entityContent = new VerticalLayout();

        entityContent.setPadding(false);
        entityContent.setSpacing(false);

        if (entity.getStructure() != null && entity.getStructure().getCharacter() != null) {
            String charString = entity.getStructure().getCharacter().getString();
            String quocNgu = "";

            if (entity.getMeaning() != null && entity.getPronunciation() != null) {
                quocNgu = entity.getPronunciation().getQuocNgu().getDescription();
            }

            if (entity.isAttested()) {
                RouterLink header = new RouterLink(charString + " " + quocNgu, EntityDetailView.class, entity.getId());
                header.getStyle()
                        .set("margin", "0 0 10px 0")
                        .set("color", "#667eea")
                        .set("font-size", "1.5em");
                H5 description = new H5(entity.getDescription());
                description.getStyle()
                        .set("margin", "0 0 0 20px")
                        .set("color", "#999")
                        .set("font-size", "1em");
                entityContent.add(header, description);
            } else {
                H3 header = new H3(charString + " " + quocNgu);
                H5 description = new H5(entity.getDescription());
                header.getStyle()
                        .set("margin", "0 0 10px 0")
                        .set("color", "#999")
                        .set("font-size", "1.5em");
                description.getStyle()
                        .set("margin", "0 0 0 20px")
                        .set("color", "#999")
                        .set("font-size", "1em");
                entityContent.add(header, description);
            }
        }

        if (entity.getMeaning() != null) {
            Paragraph description = new Paragraph();
            description.getStyle()
                    .set("margin", "0")
                    .set("color", "#555");
            for (Explanation explanation: entity.getMeaning().getExplanations()) {
                description.add(explanation.getDescription());
                description.add(new Html("<br/>"));
            }
            entityContent.add(description);
        }


        HorizontalLayout hLayout = new HorizontalLayout();
        for (int i = 0; i < entityEvolutions.size(); i++) {
//            VerticalLayout entityContent = new VerticalLayout();
            EntityEvolution entityEvolution = entityEvolutions.get(i);

            VerticalLayout evolutionLayout = new VerticalLayout();
            evolutionLayout.setSpacing(false);
            evolutionLayout.setMargin(false);
            evolutionLayout.setPadding(false);

            Paragraph evolutionDescription = new Paragraph("^ " + entityEvolution.getDescription());
            evolutionDescription.getStyle().set("margin", "0 0 10px 0")
                    .set("color", "#333")
                    .set("color", "#555");
            evolutionLayout.add(evolutionDescription);

            evolutionLayout.add(drawEntityEvolution(entityEvolution.getFromEntity(), depth + 1));
            hLayout.add(evolutionLayout);

        }
        layout.add(entityContent, hLayout);

        layout.setPadding(false);
        layout.setMargin(false);
        layout.getStyle().setWidth("100%");
        layout.getStyle().setGap("0px");
        return layout;
    }

}
