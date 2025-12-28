package com.liu.trachunom.view;

import com.liu.trachunom.entity.*;
import com.liu.trachunom.service.*;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("entity")
@PageTitle("Chi tiết nghĩa - Tra Chữ Nôm")
@NoArgsConstructor
public class EntityDetailView extends VerticalLayout implements HasUrlParameter<Long> {

    @Autowired private EntityService entityService;
    private VerticalLayout contentLayout = new VerticalLayout();
    @Autowired private EntityEvolutionService entityEvolutionService;
    @Autowired private VisualTool visualTool;
    @Autowired private EntityCompositionService entityCompositionService;
    @Autowired
    private EntityExampleService entityExampleService;

    @PostConstruct
    public void init() {
        setSizeFull();
        setSpacing(true);
        setPadding(false);
        getStyle().set("background", "#f5f5f5");

        // Header
        HorizontalLayout header = createHeader();

        // Content area
        contentLayout.setWidth("100%");
        contentLayout.getStyle()
            .set("margin", "0 auto")
            .set("background", "white")
            .set("padding", "30px")
            .set("border-radius", "8px")
            .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)")
            .set("max-width", "1000px");

        add(header, contentLayout);
        setAlignItems(Alignment.CENTER);
    }

    private HorizontalLayout createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.getStyle()
            .set("background", "linear-gradient(135deg, #667eea 0%, #764ba2 100%)")
            .set("padding", "20px 40px")
            .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)");

        // Logo/Title
        H1 title = new H1("Tra Chữ Nôm");
        title.getStyle()
            .set("color", "white")
            .set("margin", "0")
            .set("font-size", "28px");

        // Home button
        Button homeButton = new Button("Trang chủ");
        homeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        homeButton.getStyle()
            .set("color", "white")
            .set("border", "1px solid white");
        homeButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("")));

        header.add(title, homeButton);
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);

        return header;
    }

    private void displayEntityDetail(EntityX entity) {
        contentLayout.removeAll();

        // Back button
        Button backButton = new Button("← Quay lại");
        backButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        backButton.addClickListener(e -> getUI().ifPresent(ui -> ui.getPage().getHistory().back()));
        backButton.getStyle().set("margin-bottom", "20px");

        // Character display
        if (entity.getStructure() != null && entity.getStructure().getCharacter() != null) {

            HorizontalLayout characterLayout = new HorizontalLayout();
            characterLayout.setWidthFull();

            CharacterX character = entity.getStructure().getCharacter();

            VerticalLayout characterSection = new VerticalLayout();
            characterSection.setPadding(false);
            characterSection.setSpacing(false);

            Div characterDisplay = new Div();
            characterDisplay.setText(character.getString());
            characterDisplay.getStyle()
                .set("font-size", "100px")
                .set("text-align", "center")
                .set("color", "#667eea")
                .set("margin", "20px 0")
                .set("font-weight", "bold");
            characterSection.add(characterDisplay);

            // Unicode
            Paragraph unicodePara = new Paragraph();
            Span unicodeLabel = new Span("Mã Unicode: ");
            unicodeLabel.getStyle().set("font-weight", "bold");
            Span unicodeValue = new Span("U+" + Integer.toHexString(character.getUnicode()).toUpperCase());
            unicodePara.add(unicodeLabel, unicodeValue);

            characterSection.add(unicodePara);

            // Radical
            if (character.getRadical() != null) {
                Paragraph radicalPara = new Paragraph();
                Span radicalLabel = new Span("Bộ thủ: ");
                radicalLabel.getStyle().set("font-weight", "bold");
                Span radicalValue = new Span(character.getRadical().getString() +
                        " (U+" + Integer.toHexString(character.getRadical().getUnicode()).toUpperCase() + ")");
                radicalPara.add(radicalLabel, radicalValue);
                characterSection.add(radicalPara);
            }

            // Stroke count
            Integer totalStrokes = character.getTotalStrokeNumber();
            if (totalStrokes == null && character.getRadical() != null) {
                totalStrokes = character.getRadical().getStrokeNumber();
                if (character.getAdditionalStrokeNumber() != null) {
                    totalStrokes += character.getAdditionalStrokeNumber();
                }
            }
            if (totalStrokes != null) {
                Paragraph strokePara = new Paragraph();
                Span strokeLabel = new Span("Số nét: ");
                strokeLabel.getStyle().set("font-weight", "bold");
                Span strokeValue = new Span(totalStrokes.toString());
                strokePara.add(strokeLabel, strokeValue);
                characterSection.add(strokePara);
            }


            VerticalLayout characterInfoLayout = new VerticalLayout();
            characterInfoLayout.setPadding(false);
            characterInfoLayout.setSpacing(false);


            // Description section
            if (entity.getDescription() != null && !entity.getDescription().isEmpty()) {
                VerticalLayout descSection = createSection("Mô tả");
                Paragraph descPara = new Paragraph(entity.getDescription());
                descPara.getStyle().set("color", "#666");
                descSection.add(descPara);
                characterInfoLayout.add(descSection);
            }

            // Structure info
            if (entity.getStructure() != null) {
                Structure structure = entity.getStructure();
                VerticalLayout structureSection = createSection("Cấu tạo");

                if (structure.getStructureComponents() != null) {
                    Paragraph classificationPara = new Paragraph();
                    HorizontalLayout value = visualTool.drawStructure(structure);
                    value.getStyle().set("width", "800px");
                    classificationPara.add(value);
                    structureSection.add(classificationPara);
                }

                characterInfoLayout.add(structureSection);
            }

            // Pronunciation section
            if (entity.getPronunciation() != null) {
                VerticalLayout pronunciationSection = createSection("Phát âm");

                Pronunciation pronunciation = entity.getPronunciation();
                if (pronunciation.getQuocNgu() != null) {
                    Div quocNguDiv = new Div();
                    H3 quocNguText = new H3(pronunciation.getQuocNgu().getDescription());
                    quocNguText.getStyle()
                            .set("color", "#667eea")
                            .set("margin", "10px 0");
                    quocNguDiv.add(quocNguText);

                    HorizontalLayout quocNguLayout = new HorizontalLayout();
                    quocNguLayout.add(quocNguDiv);
                    quocNguLayout.add(visualTool.drawPronunciation(pronunciation));

                    pronunciationSection.add(quocNguLayout);
                }

                characterInfoLayout.add(pronunciationSection);
            }


            // Meaning section
            if (entity.getMeaning() != null && entity.getMeaning().getExplanations() != null) {
                VerticalLayout meaningSection = createSection("Nghĩa");

                for (Explanation explanation : entity.getMeaning().getExplanations()) {
                    Div explanationDiv = new Div();
                    explanationDiv.getStyle()
                            .set("padding", "15px")
                            .set("margin", "10px 0")
                            .set("border-left", "4px solid #667eea")
                            .set("background", "#f9f9f9")
                            .set("border-radius", "4px");

                    Html descriptionHtml = new Html("<div>" + explanation.getDescription() + "</div>");
                    explanationDiv.add(descriptionHtml);

                    meaningSection.add(explanationDiv);
                }
                characterInfoLayout.add(meaningSection);
            }

            characterLayout.add(characterSection, characterInfoLayout);

            contentLayout.add(characterLayout);
        }
        displayEntityEvolution(entity);
        displaySynonyms(entity);
        displayVariances(entity);
        displayExamples(entity);

        contentLayout.addComponentAsFirst(backButton);
    }

    private void displayEntityEvolution(EntityX entity) {
        // Entity evolution section
        if (!entityEvolutionService.findByToEntityId(entity.getId()).isEmpty()) {
            VerticalLayout evolutionSection = createSection("Quá trình phát triển nghĩa");
            evolutionSection.add(visualTool.drawEntityEvolution(entity));
            contentLayout.add(evolutionSection);
        }
    }

    private void displayCompoundEntityDetail(EntityX entity) {
        contentLayout.removeAll();

        // Back button
        Button backButton = new Button("← Quay lại");
        backButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        backButton.addClickListener(e -> getUI().ifPresent(ui -> ui.getPage().getHistory().back()));
        backButton.getStyle().set("margin-bottom", "20px");

        VerticalLayout compoundEntityInfoLayout = new VerticalLayout();
        compoundEntityInfoLayout.setPadding(false);
        compoundEntityInfoLayout.setSpacing(false);

        // Description section
        if (entity.getDescription() != null && !entity.getDescription().isEmpty()) {
            VerticalLayout descSection = createSection("Mô tả");
            Paragraph descPara = new Paragraph(entity.getDescription());
            descPara.getStyle().set("color", "#666");
            descSection.add(descPara);
            compoundEntityInfoLayout.add(descSection);
        }

        VerticalLayout analysationLayout = createSection("Phân tích");
        HorizontalLayout analysationHorizontalLayout = new HorizontalLayout();
        analysationHorizontalLayout.setSpacing(false);

        List<EntityComposition> entityCompositions = entityCompositionService.findByParentEntityId(entity.getId());
        if (!entityCompositions.isEmpty()) {
            for (EntityComposition composition : entityCompositions) {
                EntityX childEntity = composition.getChildEntity();
                String hnomString = entityService.getHnomString(childEntity);
                String qnguString = entityService.getQnguString(childEntity);

                VerticalLayout childEntityLayout = new VerticalLayout();
                VerticalLayout hnomLayout = new  VerticalLayout();
                VerticalLayout qnguLayout = new VerticalLayout();
                hnomLayout.add(hnomString);
                qnguLayout.add(qnguString);
                childEntityLayout.add(hnomLayout,  qnguLayout);
                hnomLayout.getStyle()
                        .set("font-size", "100px")
                        .set("text-align", "center")
                        .set("color", "#667eea")
                        .set("font-weight", "bold");
                qnguLayout.getStyle()
                        .set("color", "#667eea")
                        .set("font-weight", "bold")
                        .set("text-size", "20px");
                childEntityLayout.setMargin(false);
                childEntityLayout.setPadding(false);
                childEntityLayout.setSpacing(false);

                RouterLink link = new RouterLink(EntityDetailView.class, childEntity.getId());
                link.add(childEntityLayout);
                analysationHorizontalLayout.add(link);
            }
        }
        analysationLayout.add(analysationHorizontalLayout);
        compoundEntityInfoLayout.add(analysationLayout);

        // Meaning section
        if (entity.getMeaning() != null && entity.getMeaning().getExplanations() != null) {
            VerticalLayout meaningSection = createSection("Nghĩa");

            for (Explanation explanation : entity.getMeaning().getExplanations()) {
                Div explanationDiv = new Div();
                explanationDiv.getStyle()
                        .set("padding", "15px")
                        .set("margin", "10px 0")
                        .set("border-left", "4px solid #667eea")
                        .set("background", "#f9f9f9")
                        .set("border-radius", "4px");

                Html descriptionHtml = new Html("<div>" + explanation.getDescription() + "</div>");
                explanationDiv.add(descriptionHtml);

                meaningSection.add(explanationDiv);
            }
            compoundEntityInfoLayout.add(meaningSection);
        }

        contentLayout.add(compoundEntityInfoLayout);

        // Entity evolution section
        if (!entityEvolutionService.findByToEntityId(entity.getId()).isEmpty()) {
            VerticalLayout evolutionSection = createSection("Quá trình phát triển nghĩa");
            evolutionSection.add(visualTool.drawEntityEvolution(entity));
            contentLayout.add(evolutionSection);
        }
        displayEntityEvolution(entity);
        displaySynonyms(entity);
        displayExamples(entity);

        contentLayout.addComponentAsFirst(backButton);
    }

    private VerticalLayout createSection(String title) {
        VerticalLayout section = new VerticalLayout();
        section.setPadding(false);
        section.setSpacing(false);
        section.getStyle().set("margin-top", "20px");

        H3 sectionTitle = new H3(title);
        sectionTitle.getStyle()
            .set("color", "#333")
            .set("padding-bottom", "10px")
            .set("margin-top", "0");

        section.add(sectionTitle);
        return section;
    }

    private void showError(String errorMessage) {
        contentLayout.removeAll();

        Button backButton = new Button("← Quay lại");
        backButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        backButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("")));

        H2 errorTitle = new H2("Lỗi");
        errorTitle.getStyle().set("color", "#d32f2f");

        Paragraph errorText = new Paragraph(errorMessage);
        errorText.getStyle().set("color", "#666");

        contentLayout.add(backButton, errorTitle, errorText);
        contentLayout.setAlignItems(Alignment.CENTER);
    }

    @Override
    public void setParameter(BeforeEvent event, Long entityId) {
        if (entityId == null) {
            showError("Không tìm thấy thông tin nghĩa");
            return;
        }

        try {
            EntityX entity = entityService.findById(entityId);
            if (entity == null) {
                showError("Không tìm thấy thông tin nghĩa với ID: " + entityId);
                return;
            }
            if (!entity.isCompound()) {
                displayEntityDetail(entity);
            } else {
                displayCompoundEntityDetail(entity);
            }
        } catch (Exception e) {
            showError("Lỗi: " + e.getMessage());
        }
    }

    public void displaySynonyms(EntityX entity) {
        List<EntityX> synonyms = entityService.findSynonyms(entity);
        if (synonyms.isEmpty()) {
            return;
        }
        VerticalLayout synonymSection = createSection("Từ đồng nghĩa");
        HorizontalLayout synonymLayout = new HorizontalLayout();
        for (EntityX synonym : synonyms) {
            String hnomString = entityService.getHnomString(synonym);
            String qnguString = entityService.getQnguString(synonym);
            VerticalLayout synonymEntityLayout = new VerticalLayout();
            VerticalLayout hnomLayout = new VerticalLayout();
            VerticalLayout qnguLayout = new VerticalLayout();
            hnomLayout.add(hnomString);
            qnguLayout.add(qnguString);
            RouterLink link = new RouterLink(EntityDetailView.class, synonym.getId());
            link.add(hnomLayout);
            synonymEntityLayout.add(qnguLayout, link);
            hnomLayout.getStyle()
                    .set("font-size", "40px")
                    .set("text-align", "center")
                    .set("color", "black");
            qnguLayout.getStyle()
                    .set("color", "black")
                    .set("font-size", "12px");
            synonymEntityLayout.setMargin(false);
            synonymEntityLayout.setPadding(false);
            synonymEntityLayout.setSpacing(false);
            synonymEntityLayout.getStyle()
                    .set("align-items", "center")
                    .set("width", "fit-content");
            synonymLayout.add(synonymEntityLayout);
        }
        synonymSection.add(synonymLayout);
        contentLayout.add(synonymSection);
    }

    public void displayVariances(EntityX entity) {
        List<EntityX> variances = entityService.findVariances(entity);
        if (variances.isEmpty()) {
            return;
        }
        VerticalLayout varianceSection = createSection("Dị thể");
        HorizontalLayout varianceLayout = new HorizontalLayout();
        for (EntityX variance : variances) {
            String hnomString = entityService.getHnomString(variance);
            VerticalLayout varianceEntityLayout = new VerticalLayout();
            VerticalLayout hnomLayout = new VerticalLayout();
            hnomLayout.add(hnomString);
            hnomLayout.getStyle()
                    .set("font-size", "40px")
                    .set("text-align", "center")
                    .set("color", "black");
            varianceEntityLayout.add(hnomLayout);
            varianceEntityLayout.setMargin(false);
            varianceEntityLayout.setPadding(false);
            varianceEntityLayout.setSpacing(false);
            RouterLink link = new RouterLink(EntityDetailView.class, variance.getId());
            link.add(varianceEntityLayout);
            varianceLayout.add(link);
        }
        varianceSection.add(varianceLayout);
        contentLayout.add(varianceSection);
    }

    public void displayExamples(EntityX entity) {
        List<EntityExample> entityExamples = entityExampleService.findByEntityId(entity.getId());
        if (entityExamples.isEmpty()) {
            return;
        }
        VerticalLayout exampleSection = createSection("Ví dụ");
        for (EntityExample entityExample : entityExamples) {
            exampleSection.add(visualTool.drawExample(entityExample.getExample()));
        }
        contentLayout.add(exampleSection);
    }
}
