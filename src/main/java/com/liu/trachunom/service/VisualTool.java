package com.liu.trachunom.service;

import com.liu.trachunom.entity.*;
import com.liu.trachunom.view.EntityDetailView;
import com.liu.trachunom.view.SearchView;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VisualTool {
    private final StructureClassificationService structureClassificationService;
    private final PronunciationClassificationService pronunciationClassificationService;
    private final PronunciationEvolutionService pronunciationEvolutionService;
    private final EntityEvolutionService entityEvolutionService;
    private final EntityService entityService;
    private final ExampleWordService exampleWordService;

    private final int MAX_DEPTH = 50;

    public HorizontalLayout drawStructure(Structure structure) {
        return drawStructure(structure.getStructureComponents());
    }

    public HorizontalLayout drawStructure(List<StructureComponent> structureComponents) {
        try {
            HorizontalLayout layout = drawStructure(structureComponents, 0);
            if (layout.getChildren().findAny().isEmpty()) {
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
            H5 content = new H5(structureComponent.getStructureComponent().getCharacterString() + (quantity > 1 ? " x" + quantity : ""));
            content.getStyle().setColor("white");

            RouterLink link = new RouterLink("", SearchView.class);
            QueryParameters params = new QueryParameters(Map.of("query",
                    List.of(structureComponent.getStructureComponent().getCharacterString())));
            link.setQueryParameters(params);
            link.add(content);


            VerticalLayout container = new VerticalLayout();
            container.setPadding(false);
            container.setMargin(false);
            container.add(link);

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

        if (entity.getStructure() != null) {
            String charString = entity.getCharacterString();
            String quocNgu = "";

            if (entity.getMeaning() != null && entity.getPronunciation() != null) {
                quocNgu = entity.getPronunciation().getQuocNgu().getDescription();
            }

            Paragraph languageHeader = new Paragraph("(" + entity.getLanguage().getAbbreviation() + ")");
            languageHeader.getStyle()
                    .set("background-color", "red")
                    .set("color", "white")
                    .set("font-weight", "bold")
                    .set("font-size", "20px");
            if (entity.isAttested()) {
                RouterLink headerLink = new RouterLink(charString + " " + quocNgu, EntityDetailView.class, entity.getId());
                headerLink.getStyle()
                        .set("color", "#667eea")
                        .set("font-weight", "bold")
                        .set("font-size", "20px")
                        .set("text-decoration", "none");
                Paragraph header = new Paragraph(headerLink);

                H5 description = new H5(entity.getDescription());
                description.getStyle()
                        .set("margin", "0 0 0 20px")
                        .set("color", "#999")
                        .set("font-size", "1em");

                EntityX standardisedEntity = entityService.findStandardByEntity(entity);
                if (standardisedEntity != null) {
                    RouterLink standardLink = new RouterLink("->" + standardisedEntity.getCharacterString(), EntityDetailView.class, standardisedEntity.getId());
                    standardLink.getStyle()
                            .set("color", "white")
                            .set("background-color", "red")
                            .set("font-size", "20px")
                            .set("text-decoration", "none")
                            .set("padding", "2px 8px")
                            .set("border-radius", "4px")
                            .set("display", "inline-block");

                    Paragraph standardisedHeader = new Paragraph(standardLink);
                    entityContent.add(new HorizontalLayout(languageHeader, header, standardisedHeader), description);
                } else {
                    entityContent.add(new HorizontalLayout(languageHeader, header), description);
                }
            } else {
                H3 header = new H3(charString + " " + quocNgu);
                header.getStyle()
                        .set("color", "#999")
                        .set("font-size", "1.5em");
                H5 description = new H5(entity.getDescription());
                description.getStyle()
                        .set("margin", "0 0 0 20px")
                        .set("color", "#999")
                        .set("font-size", "1em");
                entityContent.add(new HorizontalLayout(languageHeader, header), description);
            }
        }

        if (entity.getMeaning() != null) {
            Paragraph description = new Paragraph();
            description.getStyle()
                    .set("margin", "0")
                    .set("color", "#555");
            description.add(entity.getMeaning().getExplanations().getFirst().getDescription());
            if (entity.getMeaning().getExplanations().size() > 1) {
                description.add("...");
            }
            entityContent.add(description);
        }


        HorizontalLayout hLayout = new HorizontalLayout();
        for (EntityEvolution entityEvolution : entityEvolutions) {
            VerticalLayout evolutionLayout = new VerticalLayout();
            evolutionLayout.setSpacing(false);
            evolutionLayout.setMargin(false);
            evolutionLayout.setPadding(false);

            Paragraph evolutionDescription = new Paragraph("^ " + entityEvolution.getDescription());
            evolutionDescription.getStyle()
                    .set("margin", "0 0 30px 0")
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

    public HorizontalLayout drawExample(Example example) {
        HorizontalLayout layout = new HorizontalLayout();
        for (ExampleWord exampleWord : exampleWordService.findByExample(example)) {
            EntityX entity = exampleWord.getEntity();

            Paragraph qnguText = new Paragraph(entity.getPronunciationString());
            qnguText.getStyle()
                    .set("margin", "0px")
                    .set("padding", "0px")
                    .set("color", "black")
                    .set("font-size", "12px");
            Paragraph hnomText = new Paragraph(entity.getCharacterString());
            hnomText.getStyle()
                    .set("margin", "0px")
                    .set("padding", "0px")
                    .set("color", "black")
                    .set("font-size", "40px");
            RouterLink link = new RouterLink(EntityDetailView.class, entity.getId());
            link.add(hnomText);

            VerticalLayout vLayout = new VerticalLayout();
            vLayout.setPadding(false);
            vLayout.setMargin(false);
            vLayout.setSpacing(false);
            vLayout.getStyle().set("align-items", "center");
            vLayout.add(qnguText);
            vLayout.add(entity.isAttested() ? link : hnomText);

            layout.add(vLayout);
        }
        layout.setPadding(false);
        layout.setMargin(false);
//        layout.getStyle().setWidth("100%");
        layout.getStyle().setGap("0px");
        if (example.getSource() != null) {
            HorizontalLayout sourceLayout = new HorizontalLayout();
            Paragraph sourceText = new Paragraph("(" + example.getSource().getName() + ")");
            sourceText.getStyle()
                    .set("width", "200px");
            sourceLayout.add(sourceText);
            sourceLayout.setPadding(false);
            sourceLayout.setMargin(false);
            sourceLayout.setSpacing(false);
            sourceLayout.getStyle()
                    .set("align-items", "end");
            layout.add(sourceLayout);
        }
        return layout;
    }

}
