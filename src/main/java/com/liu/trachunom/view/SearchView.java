package com.liu.trachunom.view;

import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.entity.EntityX;
import com.liu.trachunom.service.CharacterService;
import com.liu.trachunom.service.EntityService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import java.util.List;

@Route("")
public class SearchView extends VerticalLayout implements BeforeEnterObserver {

    private final CharacterService characterService;
    private final EntityService entityService;

    private final VerticalLayout contentLayout = new VerticalLayout();
    private final TextField searchField = new TextField();

    public SearchView(CharacterService characterService, EntityService entityService) {
        this.characterService = characterService;
        this.entityService = entityService;

        setSizeFull();
//        setPadding(true);
        setSpacing(true);
        getStyle().set("background", "#f5f5f5");

        // Search bar (similar to fragment)
        HorizontalLayout searchBar = new Header();

        // Content area
        contentLayout.setWidth("100%");
//        contentLayout.setMaxWidth("1000px");
        contentLayout.getStyle()
            .set("margin", "0 auto")
            .set("background", "white")
            .set("padding", "30px")
            .set("border-radius", "8px")
            .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)");

        add(searchBar, contentLayout);
        setAlignItems(Alignment.CENTER);
    }

    private HorizontalLayout createSearchBar() {
        HorizontalLayout searchBar = new HorizontalLayout();
        searchBar.setWidth("100%");
        searchBar.setMaxWidth("1000px");
        searchBar.setPadding(true);
        searchBar.setSpacing(true);
        searchBar.getStyle()
            .set("background", "white")
            .set("border-radius", "8px")
            .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)")
            .set("margin-bottom", "20px");

        searchField.setPlaceholder("Nhập ký tự chữ Nôm...");
        searchField.setWidth("100%");
        searchField.getStyle().set("font-size", "16px");

        Button searchButton = new Button("Tìm Kiếm");
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchButton.addClickShortcut(Key.ENTER);
        searchButton.addClickListener(e -> performSearch());

        searchBar.add(searchField, searchButton);
        searchBar.expand(searchField);
        searchBar.setAlignItems(Alignment.CENTER);

        return searchBar;
    }

    private void performSearch() {
        String query = searchField.getValue();
        if (query != null && !query.trim().isEmpty()) {
            getUI().ifPresent(ui -> ui.navigate("?query=" + query.trim()));
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Location location = event.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        if (queryParameters == null) {
            contentLayout.removeAll();
            contentLayout.add(new IndexView());
            return;
        }

        List<String> queryList = queryParameters.getParameters().get("query");
        if (!(queryList == null || queryList.isEmpty())) {
            String query = queryList.get(0);
            searchField.setValue(query);
            searchCharacter(query);
        } else {
            contentLayout.removeAll();
            contentLayout.add(new IndexView());
        }
    }

    private void searchCharacter(String query) {
        contentLayout.removeAll();

        try {
//            if (query == null || query.trim().isEmpty()) {
//                showError("Vui lòng nhập ký tự cần tìm");
//                return;
//            }

            int codePoint = query.codePointAt(0);
            CharacterX character = characterService.findByUnicode(codePoint);

            if (character == null) {
                showError("Không tìm thấy kí tự " + new String(Character.toChars(codePoint)));
                return;
            }

            displayCharacterInfo(character);

        } catch (Exception e) {
            showError("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayCharacterInfo(CharacterX character) {
        // Title
        H1 resultTitle = new H1("Kết Quả Tìm Kiếm");
        resultTitle.getStyle()
            .set("color", "#333")
            .set("margin-top", "0");

        // Character display (large)
        Div characterDisplay = new Div();
        characterDisplay.setText(character.getString());
        characterDisplay.getStyle()
            .set("font-size", "120px")
            .set("text-align", "center")
            .set("color", "#667eea")
            .set("margin", "20px 0")
            .set("font-weight", "bold");

        // Character info section
        VerticalLayout infoSection = new VerticalLayout();
        infoSection.setPadding(false);
        infoSection.setSpacing(false);

        // Unicode
        Paragraph unicodePara = new Paragraph();
        Span unicodeLabel = new Span("Mã Unicode: ");
        unicodeLabel.getStyle().set("font-weight", "bold");
        Span unicodeValue = new Span("U+" + Integer.toHexString(character.getUnicode()).toUpperCase());
        unicodePara.add(unicodeLabel, unicodeValue);

        infoSection.add(unicodePara);

        // Radical
        if (character.getRadical() != null) {
            Paragraph radicalPara = new Paragraph();
            Span radicalLabel = new Span("Bộ thủ: ");
            radicalLabel.getStyle().set("font-weight", "bold");
            Span radicalValue = new Span(character.getRadical().getString() +
                " (U+" + Integer.toHexString(character.getRadical().getUnicode()).toUpperCase() + ")");
            radicalPara.add(radicalLabel, radicalValue);
            infoSection.add(radicalPara);
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
            infoSection.add(strokePara);
        }

        // Meaning section
        Paragraph meaningTitle = new Paragraph();
        Span meaningLabel = new Span("Ý nghĩa:");
        meaningLabel.getStyle().set("font-weight", "bold");
        meaningTitle.add(meaningLabel);
        infoSection.add(meaningTitle);

        // Entities (meanings and pronunciations)
        List<EntityX> entities = entityService.findByCharacter(character);
        VerticalLayout entitiesSection = new VerticalLayout();
        entitiesSection.setPadding(false);
        entitiesSection.setSpacing(true);

        if (entities != null && !entities.isEmpty()) {
            for (EntityX entity : entities) {
                Div entityDiv = new Div();
                entityDiv.getStyle()
                    .set("padding", "15px")
                    .set("margin", "10px 0")
                    .set("border-left", "4px solid #667eea")
                    .set("background", "#f9f9f9")
                    .set("border-radius", "4px");

                VerticalLayout entityContent = new VerticalLayout();
                entityContent.setPadding(false);
                entityContent.setSpacing(false);

                if (entity.getStructure() != null && entity.getStructure().getCharacter() != null) {
                    String charString = entity.getStructure().getCharacter().getString();
                    String quocNgu = "";

                    if (entity.getMeaning() != null && entity.getPronunciation() != null) {
                        quocNgu = entity.getPronunciation().getQuocNgu().getDescription();
                    }

                    H5 header = new H5(charString + " " + quocNgu);
                    header.getStyle()
                        .set("margin", "0 0 10px 0")
                        .set("color", "#667eea");
                    entityContent.add(header);
                }

                if (entity.getMeaning() != null) {
                    if (entity.getMeaning().getExplanations() != null) {
                        Paragraph description = new Paragraph(entity.getMeaning().getExplanations().getFirst().getDescription());
                        description.getStyle()
                            .set("margin", "0")
                            .set("color", "#555");
                        entityContent.add(description);
                    }
                }

                entityDiv.add(entityContent);
                entitiesSection.add(entityDiv);
            }
        } else {
            Paragraph noMeaning = new Paragraph("Chưa có thông tin ý nghĩa cho ký tự này.");
            noMeaning.getStyle().set("color", "#999");
            entitiesSection.add(noMeaning);
        }

        contentLayout.add(resultTitle, characterDisplay, infoSection, entitiesSection);
    }

    private void showError(String errorMessage) {
        contentLayout.removeAll();

        H2 errorTitle = new H2("Lỗi");
        errorTitle.getStyle().set("color", "#d32f2f");

        Paragraph errorText = new Paragraph(errorMessage);
        errorText.getStyle().set("color", "#666");

        contentLayout.add(errorTitle, errorText);
        contentLayout.setAlignItems(Alignment.CENTER);
    }
}
