package com.liu.trachunom.view;

import com.liu.trachunom.dto.*;
import com.liu.trachunom.entity.*;
import com.liu.trachunom.service.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.ArrayList;
import java.util.List;

@Route("admin/dictionary-managementx")
public class DictionaryManagementViewX extends VerticalLayout {

    private final RadicalService radicalService;
    private final ClassificationService classificationService;
    private final LanguageService languageService;
    private final CharacterService characterService;
    private final StructureService structureService;
    private final MeaningService meaningService;
    private final EntityService entityService;

    private List<Radical> radicals;
    private List<Classification> classifications;
    private List<Language> languages;

    public DictionaryManagementViewX(
            RadicalService radicalService,
            ClassificationService classificationService,
            LanguageService languageService,
            CharacterService characterService,
            StructureService structureService,
            MeaningService meaningService,
            EntityService entityService) {

        this.radicalService = radicalService;
        this.classificationService = classificationService;
        this.languageService = languageService;
        this.characterService = characterService;
        this.structureService = structureService;
        this.meaningService = meaningService;
        this.entityService = entityService;

        setSizeFull();
        setPadding(true);
        setSpacing(true);
        getStyle().set("background", "#f5f5f5");

        // Load reference data
        loadReferenceData();

        // Header
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);

        H1 title = new H1("Quản lý từ điển");
        title.getStyle().set("margin", "0");

        RouterLink homeLink = new RouterLink("← Quay về", IndexView.class);
        homeLink.getStyle()
            .set("text-decoration", "none")
            .set("color", "#667eea")
            .set("font-weight", "500");

        header.add(homeLink, title);

        // Create tabs
        Tab characterTab = new Tab("Ký tự");
        Tab structureTab = new Tab("Cấu tạo");
        Tab meaningTab = new Tab("Ý nghĩa");
        Tab entityTab = new Tab("Thực thể");

        Tabs tabs = new Tabs(characterTab, structureTab, meaningTab, entityTab);
        tabs.setWidthFull();

        // Create tab content
        VerticalLayout characterContent = createCharacterForm();
        VerticalLayout structureContent = createStructureForm();
        VerticalLayout meaningContent = createMeaningForm();
        VerticalLayout entityContent = createEntityForm();

        // Show first tab by default
        characterContent.setVisible(true);
        structureContent.setVisible(false);
        meaningContent.setVisible(false);
        entityContent.setVisible(false);

        // Tab selection listener
        tabs.addSelectedChangeListener(event -> {
            characterContent.setVisible(false);
            structureContent.setVisible(false);
            meaningContent.setVisible(false);
            entityContent.setVisible(false);

            Tab selectedTab = event.getSelectedTab();
            if (selectedTab == characterTab) {
                characterContent.setVisible(true);
            } else if (selectedTab == structureTab) {
                structureContent.setVisible(true);
            } else if (selectedTab == meaningTab) {
                meaningContent.setVisible(true);
            } else if (selectedTab == entityTab) {
                entityContent.setVisible(true);
            }
        });

        add(header, tabs, characterContent, structureContent, meaningContent, entityContent);
    }

    private void loadReferenceData() {
        try {
            radicals = radicalService.findAll();
            classifications = classificationService.findAll();
            languages = languageService.findAll();

            // Ensure lists are never null
            if (radicals == null) radicals = new ArrayList<>();
            if (classifications == null) classifications = new ArrayList<>();
            if (languages == null) languages = new ArrayList<>();
        } catch (Exception e) {
            // Initialize empty lists if loading fails
            radicals = new ArrayList<>();
            classifications = new ArrayList<>();
            languages = new ArrayList<>();
            showError("Không thể tải dữ liệu tham chiếu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private VerticalLayout createCharacterForm() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMaxWidth("800px");
        layout.getStyle()
            .set("background", "white")
            .set("padding", "30px")
            .set("border-radius", "8px")
            .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)")
            .set("margin", "0 auto");

        H2 formTitle = new H2("Thêm Ký tự");

        // Character input
        TextField characterField = new TextField("Ký tự");
        characterField.setPlaceholder("Nhập ký tự...");
        characterField.setRequired(true);
        characterField.setWidth("100%");

        // Radical selection with radio buttons
        H3 radicalTitle = new H3("Bộ thủ");

        RadioButtonGroup<Radical> radicalGroup = new RadioButtonGroup<>();
        radicalGroup.setItems(radicals);
        radicalGroup.setItemLabelGenerator(r -> r.getString() + " (" + r.getId() + ")");

        // Display radicals in grid layout
        Div radicalGrid = new Div();
        radicalGrid.getStyle()
            .set("display", "grid")
            .set("grid-template-columns", "repeat(10, 1fr)")
            .set("gap", "10px")
            .set("margin", "15px 0");

        for (Radical radical : radicals) {
            Button radicalBtn = new Button(radical.getString());
            radicalBtn.getStyle()
                .set("font-size", "20px")
                .set("padding", "10px");
            radicalBtn.addClickListener(e -> {
                radicalGroup.setValue(radical);
            });
            radicalGrid.add(radicalBtn);
        }

        // Stroke numbers
        IntegerField additionalStrokeField = new IntegerField("Số nét ngoài bộ");
        additionalStrokeField.setMin(0);
        additionalStrokeField.setWidth("100%");

        IntegerField totalStrokeField = new IntegerField("Tổng số nét");
        totalStrokeField.setMin(1);
        totalStrokeField.setRequired(true);
        totalStrokeField.setWidth("100%");

        // Submit button
        Button submitButton = new Button("Lưu ký tự");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        submitButton.addClickListener(e -> {
            try {
                String charStr = characterField.getValue();
                if (charStr == null || charStr.trim().isEmpty()) {
                    showError("Vui lòng nhập ký tự");
                    return;
                }
                if (radicalGroup.getValue() == null) {
                    showError("Vui lòng chọn bộ thủ");
                    return;
                }
                if (totalStrokeField.getValue() == null) {
                    showError("Vui lòng nhập tổng số nét");
                    return;
                }

                CharacterDto dto = new CharacterDto();
                dto.setCharacter(charStr.trim());
                dto.setRadicalId(radicalGroup.getValue().getId());
                dto.setAdditionalStrokeNumber(additionalStrokeField.getValue());
                dto.setTotalStrokeNumber(totalStrokeField.getValue());

                characterService.save(dto);
                showSuccess("Lưu ký tự thành công");

                // Clear form
                characterField.clear();
                radicalGroup.clear();
                additionalStrokeField.clear();
                totalStrokeField.clear();

            } catch (Exception ex) {
                showError("Lỗi: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        layout.add(formTitle, characterField, radicalTitle, radicalGrid,
                   additionalStrokeField, totalStrokeField, submitButton);
        return layout;
    }

    private VerticalLayout createStructureForm() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMaxWidth("800px");
        layout.getStyle()
            .set("background", "white")
            .set("padding", "30px")
            .set("border-radius", "8px")
            .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)")
            .set("margin", "0 auto");

        H2 formTitle = new H2("Thêm Cấu tạo");

        // Structure ID
        IntegerField structureIdField = new IntegerField("ID cấu tạo");
        structureIdField.setPlaceholder("(tự động tạo)");
        structureIdField.setWidth("100%");

        // Character input
        TextField characterField = new TextField("Ký tự");
        characterField.setPlaceholder("Nhập ký tự cần thêm cấu tạo...");
        characterField.setRequired(true);
        characterField.setWidth("100%");

        // Sub-structures section
        H3 subStructureTitle = new H3("Cấu tạo thuộc kiểu");

        VerticalLayout subStructuresLayout = new VerticalLayout();
        subStructuresLayout.setPadding(false);
        subStructuresLayout.setSpacing(true);

        List<SubStructureFormRow> subStructureRows = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            SubStructureFormRow row = new SubStructureFormRow(i + 1, classifications);
            subStructuresLayout.add(row.getLayout());
            subStructureRows.add(row);
        }

        // Preview section
        H3 previewTitle = new H3("Xem trước");
        Div previewDiv = new Div();
        previewDiv.getStyle()
            .set("padding", "20px")
            .set("background", "#f9f9f9")
            .set("border-radius", "4px")
            .set("min-height", "100px");

        // Submit button
        Button submitButton = new Button("Lưu cấu tạo");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        submitButton.addClickListener(e -> {
            try {
                String charStr = characterField.getValue();
                if (charStr == null || charStr.trim().isEmpty()) {
                    showError("Vui lòng nhập ký tự");
                    return;
                }

                StructureFormDto dto = new StructureFormDto();
                dto.setStructureDto(new StructureDto());
                dto.getStructureDto().setString(charStr.trim());

                if (structureIdField.getValue() != null) {
                    dto.getStructureDto().setId(structureIdField.getValue().longValue());
                }

                // Collect sub-structures
                List<SubStructureDto> subStructures = new ArrayList<>();
                for (SubStructureFormRow row : subStructureRows) {
                    SubStructureDto subDto = row.getSubStructureDto();
                    if (subDto != null) {
                        subStructures.add(subDto);
                    }
                }
                dto.setSubStructures(subStructures);

                structureService.save(dto);
                showSuccess("Lưu cấu tạo thành công");

                // Clear form
                structureIdField.clear();
                characterField.clear();
                subStructureRows.forEach(SubStructureFormRow::clear);

            } catch (Exception ex) {
                showError("Lỗi: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        layout.add(formTitle, structureIdField, characterField,
                   subStructureTitle, subStructuresLayout,
                   previewTitle, previewDiv, submitButton);
        return layout;
    }

    private VerticalLayout createMeaningForm() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMaxWidth("800px");
        layout.getStyle()
            .set("background", "white")
            .set("padding", "30px")
            .set("border-radius", "8px")
            .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)")
            .set("margin", "0 auto");

        H2 formTitle = new H2("Thêm Ý nghĩa");

        // Meaning ID
        IntegerField meaningIdField = new IntegerField("ID ý nghĩa");
        meaningIdField.setPlaceholder("(tự động tạo)");
        meaningIdField.setWidth("100%");

        // Description
        TextArea descriptionField = new TextArea("Mô tả / Nghĩa");
        descriptionField.setPlaceholder("Nhập nghĩa...");
        descriptionField.setRequired(true);
        descriptionField.setWidth("100%");
        descriptionField.setHeight("150px");

        // Submit button
        Button submitButton = new Button("Lưu nghĩa");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        submitButton.addClickListener(e -> {
            try {
                if (descriptionField.getValue() == null || descriptionField.getValue().trim().isEmpty()) {
                    showError("Vui lòng nhập nghĩa");
                    return;
                }

                MeaningDto dto = new MeaningDto();
                if (meaningIdField.getValue() != null) {
                    dto.setId(meaningIdField.getValue().longValue());
                }
                dto.setDescription(descriptionField.getValue().trim());

                meaningService.save(dto);
                showSuccess("Lưu nghĩa thành công");

                // Clear form
                meaningIdField.clear();
                descriptionField.clear();

            } catch (Exception ex) {
                showError("Lỗi: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        layout.add(formTitle, meaningIdField, descriptionField, submitButton);
        return layout;
    }

    private VerticalLayout createEntityForm() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMaxWidth("800px");
        layout.getStyle()
            .set("background", "white")
            .set("padding", "30px")
            .set("border-radius", "8px")
            .set("box-shadow", "0 2px 10px rgba(0,0,0,0.1)")
            .set("margin", "0 auto");

        H2 formTitle = new H2("Thêm Thực thể");

        // Entity ID
        IntegerField entityIdField = new IntegerField("ID thực thể");
        entityIdField.setPlaceholder("(tự động tạo)");
        entityIdField.setWidth("100%");

        // Meaning ID
        IntegerField meaningIdField = new IntegerField("ID ý nghĩa");
        meaningIdField.setRequired(true);
        meaningIdField.setWidth("100%");

        // Structure ID
        IntegerField structureIdField = new IntegerField("ID cấu tạo");
        structureIdField.setRequired(true);
        structureIdField.setWidth("100%");

        // Submit button
        Button submitButton = new Button("Lưu thực thể");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        submitButton.addClickListener(e -> {
            try {
                if (meaningIdField.getValue() == null) {
                    showError("Vui lòng nhập ID ý nghĩa");
                    return;
                }
                if (structureIdField.getValue() == null) {
                    showError("Vui lòng nhập ID cấu tạo");
                    return;
                }

                EntityDto dto = new EntityDto();
                if (entityIdField.getValue() != null) {
                    dto.setId(entityIdField.getValue().longValue());
                }
                dto.setMeaningId(meaningIdField.getValue().longValue());
                dto.setStructureId(structureIdField.getValue().longValue());

                entityService.save(dto);
                showSuccess("Lưu thực thể thành công");

                // Clear form
                entityIdField.clear();
                meaningIdField.clear();
                structureIdField.clear();

            } catch (Exception ex) {
                showError("Lỗi: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        layout.add(formTitle, entityIdField, meaningIdField, structureIdField, submitButton);
        return layout;
    }

    private void showSuccess(String message) {
        Notification notification = Notification.show(message, 3000, Notification.Position.TOP_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private void showError(String message) {
        Notification notification = Notification.show(message, 5000, Notification.Position.TOP_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }

    // Helper class for sub-structure form rows
    private static class SubStructureFormRow {
        private final HorizontalLayout layout;
        private final IntegerField subStructureIdField;
        private final IntegerField quantityField;
        private final ComboBox<Classification> classificationCombo;

        public SubStructureFormRow(int index, List<Classification> classifications) {
            layout = new HorizontalLayout();
            layout.setAlignItems(Alignment.CENTER);
            layout.setWidth("100%");

            subStructureIdField = new IntegerField();
            subStructureIdField.setPlaceholder(String.valueOf(index));
            subStructureIdField.setWidth("150px");

            Span multiplyLabel = new Span(" × ");

            quantityField = new IntegerField();
            quantityField.setWidth("80px");
            quantityField.setMin(1);
            quantityField.setValue(1);

            classificationCombo = new ComboBox<>();
            classificationCombo.setItems(classifications);
            classificationCombo.setItemLabelGenerator(Classification::getDescription);
            classificationCombo.setWidth("200px");

            layout.add(subStructureIdField, multiplyLabel, quantityField, classificationCombo);
        }

        public HorizontalLayout getLayout() {
            return layout;
        }

        public SubStructureDto getSubStructureDto() {
            if (subStructureIdField.getValue() == null) {
                return null;
            }

            SubStructureDto dto = new SubStructureDto();
            dto.setSubStructureId(subStructureIdField.getValue().longValue());
            dto.setQuantity(quantityField.getValue());
            if (classificationCombo.getValue() != null) {
                dto.setClassificationId(classificationCombo.getValue().getId());
            }
            return dto;
        }

        public void clear() {
            subStructureIdField.clear();
            quantityField.setValue(1);
            classificationCombo.clear();
        }
    }
}
