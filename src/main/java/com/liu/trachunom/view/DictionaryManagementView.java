package com.liu.trachunom.view;

import com.liu.trachunom.entity.*;
import com.liu.trachunom.service.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Route("dictionary-management")
@PageTitle("Quản lí từ điển")
@UIScope
@Component
@Setter
@NoArgsConstructor
public class DictionaryManagementView extends VerticalLayout {
    @Autowired private RadicalService radicalService;
    @Autowired private StructureClassificationService structureClassificationService;
    @Autowired private LanguageService languageService;
    @Autowired private CharacterService characterService;
    @Autowired private StructureService structureService;
    @Autowired private ExplanationService explanationService;
    @Autowired private MeaningService meaningService;
    @Autowired private EntityService entityService;
    @Autowired private QuocNguService quocNguService;
    @Autowired private StyleService styleService;
    @Autowired private SourceService sourceService;
    @Autowired private TradSimpStandardService tradSimpStandardService;
    @Autowired private StructureComponentService structureComponentService;
    @Autowired private PronunciationService pronunciationService;
    @Autowired private PronunciationEvolutionService pronunciationEvolutionService;
    @Autowired private PronunciationClassificationService pronunciationClassificationService;
    @Autowired private EntityCompositionService entityCompositionService;
    @Autowired private EntityEvolutionService entityEvolutionService;
    @Autowired private VisualTool visualTool;
    @Autowired private ExampleService exampleService;
    @Autowired private ExampleWordService exampleWordService;
    @Autowired private EntityExampleService entityExampleService;

    @PostConstruct
    private void init() {
        setSizeFull(); // Chiếm toàn bộ không gian trình duyệt
        setPadding(false);
        setSpacing(false);

        Tab basicsTab = new Tab("Cơ bản");
        Tab combiningTab = new Tab("Kết hợp");
        Tab entitiesTab = new Tab("Thực thể");
        Tab complementaryTab = new Tab("Bổ sung");

        Tabs tabs = new Tabs(basicsTab, combiningTab, entitiesTab, complementaryTab);
//        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        HorizontalLayout basicsLayout = basicsLayout();
        HorizontalLayout combiningLayout = combiningLayout();
        HorizontalLayout entitiesLayout = entitiesLayout();
        HorizontalLayout complementaryLayout = complementaryLayout();

        // Đảm bảo tất cả layout chiếm toàn bộ chiều ngang
        basicsLayout.setWidthFull();
        combiningLayout.setWidthFull();
        entitiesLayout.setWidthFull();
        complementaryLayout.setWidthFull();

        basicsLayout.setVisible(true);
        combiningLayout.setVisible(false);
        entitiesLayout.setVisible(false);
        complementaryLayout.setVisible(false);

        tabs.addSelectedChangeListener(event -> {
            Tab selectedTab = event.getSelectedTab();
            basicsLayout.setVisible(selectedTab == basicsTab);
            combiningLayout.setVisible(selectedTab == combiningTab);
            entitiesLayout.setVisible(selectedTab == entitiesTab);
            complementaryLayout.setVisible(selectedTab == complementaryTab);
        });

        add(tabs, basicsLayout, combiningLayout, entitiesLayout, complementaryLayout);

    }

    private HorizontalLayout basicsLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();

        VerticalLayout characterLayout = new VerticalLayout();
        characterLayout.setSizeFull();

        H5 radicalHeader = new H5("Bộ thủ và kí tự");
        Grid<Radical> radicalGrid = new Grid<>();
        radicalGrid.setHeight("300px");
        radicalGrid.addColumn(Radical::getString).setWidth("50px").setFlexGrow(0);
        radicalGrid.addColumn(Radical::getId, "id").setHeader("Mã bộ thủ");
        radicalGrid.addColumn(Radical::getRadicalUnicode, "radicalUnicode").setHeader("Unicode bộ thủ");
        radicalGrid.addColumn(Radical::getUnicode, "description").setHeader("Unicode kí tự");
        radicalGrid.setItems(radicalService.findAll());

        HorizontalLayout radicalButtons = new HorizontalLayout();
        Button addRadicalButton = new Button("Thêm");
        Button editRadicalButton = new Button("Sửa");
        Button deleteRadicalButton = new Button("Xóa");

        addRadicalButton.addClickListener(e -> {
            Radical newRadical = Radical.builder()
                    .id("placeholder")
                    .radicalUnicode(0)
                    .unicode(0)
                    .build();
            radicalService.save(newRadical);
            radicalGrid.setItems(radicalService.findAll());
        });

        editRadicalButton.addClickListener(e -> {
            Radical radical = radicalGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (radical == null) {
                return;
            }

            // Tạo binder MỚI cho mỗi dialog
            Binder<Radical> binder = new Binder<>();
            binder.setBean(radical);
            String id = radical.getId();

            Dialog dialog = new Dialog();

            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField idField = new TextField("Mã bộ thủ");
            idField.setValue(radical.getId());
            binder.forField(idField)
                    .asRequired("Vui lòng nhập mã bộ thủ")
                    .withValidator(string -> !radicalService.existsById(string) || string.equals(id), "Đã tồn tại mã bộ thủ này")
                    .bind(Radical::getId, Radical::setId);

            IntegerField radicalUnicodeField = new IntegerField("Unicode bộ thủ");
            radicalUnicodeField.setValue(radical.getRadicalUnicode());
            binder.forField(radicalUnicodeField)
                    .withValidator(Character::isDefined, "Unicode không hợp lệ")
                    .bind(Radical::getRadicalUnicode, Radical::setRadicalUnicode);

            IntegerField unicodeField = new IntegerField("Unicode kí tự");
            unicodeField.setValue(radical.getUnicode());
            binder.forField(unicodeField)
                    .withValidator(integer -> (integer != null && Character.isDefined(integer)), "Unicode không hợp lệ")
                    .bind(Radical::getUnicode, Radical::setUnicode);

            dialogLayout.add(idField, radicalUnicodeField, unicodeField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);

            saveButton.addClickListener(ev -> {
                if (binder.validate().isOk()) {
                    Radical editedRadical = binder.getBean();
                    editedRadical.setId(idField.getValue());
                    editedRadical.setRadicalUnicode(radicalUnicodeField.getValue());
                    editedRadical.setUnicode(unicodeField.getValue());
                    radicalService.deleteById(id);
                    radicalService.save(editedRadical);
                    radicalGrid.setItems(radicalService.findAll());
                    dialog.close();
                }
            });

            cancelButton.addClickListener(ev -> {
                dialog.close();
            });

            dialog.open();
        });

        deleteRadicalButton.addClickListener(e -> {
            radicalGrid.getSelectedItems().stream().findFirst().ifPresent(radical -> {
                radicalService.deleteById(radical.getId());
                radicalGrid.setItems(radicalService.findAll());
            });
        });

        radicalButtons.add(addRadicalButton, editRadicalButton, deleteRadicalButton);

        Grid<CharacterX> characterGrid = new Grid<>();
        characterGrid.setHeight("300px");
        characterGrid.addColumn(CharacterX::getString).setWidth("50px").setFlexGrow(0);
        characterGrid.addColumn(CharacterX::getUnicode, "Unicode").setHeader("Unicode");
        characterGrid.addColumn(character -> character.getRadical().getString(), "radical)").setHeader("Bộ thủ").setWidth("75px");
        characterGrid.addColumn(CharacterX::getAdditionalStrokeNumber, "additionalStrokeNumber").setHeader("Số nét phụ");
        characterGrid.addColumn(CharacterX::getTotalStrokeNumber, "totalStrokeNumber").setHeader("Tổng số nét");
        characterGrid.setItems(characterService.findAll());

        HorizontalLayout characterButtons = new HorizontalLayout();
        Button addcharacterButton = new Button("Thêm");
        Button editcharacterButton = new Button("Sửa");
        Button deletecharacterButton = new Button("Xóa");

        addcharacterButton.addClickListener(cl -> {
            CharacterX newCharacter = CharacterX.builder()
                    .unicode(Integer.MAX_VALUE)
                    .radical(radicalService.findByUnicode("一".codePointAt(0)))
                    .additionalStrokeNumber(0)
                    .totalStrokeNumber(0)
                    .build();
            characterService.save(newCharacter);
            characterGrid.setItems(characterService.findAll());
        });

        editcharacterButton.addClickListener(cl -> {
            CharacterX character = characterGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (character == null) {
                return;
            }

            // Tạo binder MỚI cho mỗi dialog
            Binder<CharacterX> binder = new Binder<>();
            binder.setBean(character);
            Integer unicode = character.getUnicode();

            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            IntegerField unicodeField = new IntegerField("Unicode");
            unicodeField.setValue(character.getUnicode());
            binder.forField(unicodeField)
                    .withValidator(integer -> integer != null && integer > 0, "Unicode phải là số nguyên dương")
                    .withValidator(Character::isDefined, "Unicode không hợp lệ")
                    .withValidator(integer -> !characterService.existsByUnicode(integer) || integer.equals(unicode), "Đã tồn tại kí tự với Unicode này")
                    .bind(CharacterX::getUnicode, CharacterX::setUnicode);

            ComboBox<Radical> radicalField = new ComboBox<>("Bộ thủ");
            radicalField.setItems(radicalService.findAll());
            radicalField.setItemLabelGenerator((Radical radical) -> radical.getId() + " - " + radical.getString());
            radicalField.setValue(character.getRadical());
            binder.forField(radicalField).asRequired("Vui lòng chọn bộ thủ")
                    .bind(CharacterX::getRadical, CharacterX::setRadical);

            IntegerField additionalStrokeNumberField = new IntegerField("Số nét phụ");
            additionalStrokeNumberField.setValue(character.getAdditionalStrokeNumber());
            binder.forField(additionalStrokeNumberField).withValidator(Objects::nonNull, "Số nét phụ phải là số nguyên")
                    .bind(CharacterX::getAdditionalStrokeNumber, CharacterX::setAdditionalStrokeNumber);

            IntegerField totalStrokeNumberField = new IntegerField("Tổng số nét");
            totalStrokeNumberField.setValue(character.getTotalStrokeNumber());
            binder.forField(totalStrokeNumberField).withValidator(integer -> integer != null && integer > 0, "Tổng số nét phải là số nguyên dương")
                    .bind(CharacterX::getTotalStrokeNumber, CharacterX::setTotalStrokeNumber);

            dialogLayout.add(unicodeField, radicalField, additionalStrokeNumberField, totalStrokeNumberField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    CharacterX editedCharacter = binder.getBean();
                    editedCharacter.setUnicode(unicodeField.getValue());
                    editedCharacter.setRadical(radicalField.getValue());
                    editedCharacter.setAdditionalStrokeNumber(additionalStrokeNumberField.getValue());
                    editedCharacter.setTotalStrokeNumber(totalStrokeNumberField.getValue());
                    characterService.deleteByUnicode(unicode);
                    characterService.save(editedCharacter);
                    characterGrid.setItems(characterService.findAll());
                    dialog.close();
                }
            });
            cancelButton.addClickListener(cl2 -> {
                dialog.close();
            });
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deletecharacterButton.addClickListener(cl -> {
            CharacterX character = characterGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (character == null) {
                return;
            }
            characterService.deleteByUnicode(character.getUnicode());
            characterGrid.setItems(characterService.findAll());
        });

        characterButtons.add(addcharacterButton, editcharacterButton, deletecharacterButton);

        characterLayout.add(radicalHeader, radicalGrid, radicalButtons, characterGrid, characterButtons);

        VerticalLayout componentLayout = new VerticalLayout();
        componentLayout.setSizeFull();

        H5 componentHeader = new H5("Cấu tạo, âm đọc, giải nghĩa");

        Grid<QuocNgu> quocNguGrid = new Grid<>();
        quocNguGrid.setHeight("300px");
        quocNguGrid.addColumn(QuocNgu::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        quocNguGrid.addColumn(QuocNgu::getDescription, "description").setHeader("Mô tả âm đọc");
        quocNguGrid.setItems(quocNguService.findAll());
        HorizontalLayout quocNguButtons = new HorizontalLayout();
        Button addQuocNguButton = new Button("Thêm");
        Button editQuocNguButton = new Button("Sửa");
        Button deleteQuocNguButton = new Button("Xóa");
        addQuocNguButton.addClickListener(cl -> {
            QuocNgu newQuocNgu = QuocNgu.builder()
                    .description("Âm đọc mới")
                    .build();
            quocNguService.save(newQuocNgu);
            quocNguGrid.setItems(quocNguService.findAll());
        });

        editQuocNguButton.addClickListener(cl -> {
            QuocNgu quocNgu = quocNguGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (quocNgu == null) {
                return;
            }

            // Tạo binder MỚI cho mỗi dialog
            Binder<QuocNgu> binder = new Binder<>();
            binder.setBean(quocNgu);

            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField meaningField = new TextField("Mô tả âm đọc");
            meaningField.setValue(quocNgu.getDescription());
            binder.forField(meaningField)
                    .asRequired("Vui lòng nhập mô tả âm đọc")
                    .withValidator(string -> !quocNguService.existsByDescription(string.toLowerCase().trim()), "Đã tồn tại âm đọc này")
                    .bind(QuocNgu::getDescription, QuocNgu::setDescription);
            dialogLayout.add(meaningField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    QuocNgu editedQuocNgu = binder.getBean();
                    editedQuocNgu.setDescription(meaningField.getValue().toLowerCase().trim());
                    quocNguService.save(editedQuocNgu);
                    quocNguGrid.setItems(quocNguService.findAll());
                    dialog.close();
                }
            });
            cancelButton.addClickListener(cl2 -> {
                dialog.close();
            });
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteQuocNguButton.addClickListener(cl -> {
            QuocNgu quocNgu = quocNguGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (quocNgu == null) {
                return;
            }
            quocNguService.deleteById(quocNgu.getId());
            quocNguGrid.setItems(quocNguService.findAll());
        });

        quocNguButtons.add(addQuocNguButton, editQuocNguButton, deleteQuocNguButton);

        Grid<Explanation> explanationGrid = new Grid<>();
        explanationGrid.setHeight("300px");
        explanationGrid.addColumn(Explanation::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        explanationGrid.addColumn(Explanation::getDescription, "description").setHeader("Mô tả ý nghĩa");
        explanationGrid.setItems(explanationService.findAll());
        HorizontalLayout explanationButtons = new HorizontalLayout();
        Button addExplanationButton = new Button("Thêm");
        Button editExplanationButton = new Button("Sửa");
        Button deleteExplanationButton = new Button("Xóa");

        addExplanationButton.addClickListener(cl -> {
            Explanation newExplanation = Explanation.builder()
                    .description("Giải nghĩa mới")
                    .build();
            explanationService.save(newExplanation);
            explanationGrid.setItems(explanationService.findAll());
        });

        editExplanationButton.addClickListener(cl -> {
            Explanation explanation = explanationGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (explanation == null) {
                return;
            }

            // Tạo binder MỚI cho mỗi dialog
            Binder<Explanation> binder = new Binder<>();
            binder.setBean(explanation);

            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField explanationField = new TextField("Mô tả nghĩa");
            explanationField.setValue(explanation.getDescription());
            binder.forField(explanationField)
                    .asRequired("Vui lòng nhập mô tả nghĩa")
                    .withValidator(string -> !explanationService.existsByDescription(string.toLowerCase().trim(), explanation), "Đã tồn tại mô tả nghĩa này")
                    .bind(Explanation::getDescription, Explanation::setDescription);
            dialogLayout.add(explanationField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    Explanation editedExplanation = binder.getBean();
                    editedExplanation.setDescription(explanationField.getValue().toLowerCase().trim());
                    explanationService.save(editedExplanation);
                    explanationGrid.setItems(explanationService.findAll());
                    dialog.close();
                }
            });
            cancelButton.addClickListener(cl2 -> {
                dialog.close();
            });
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteExplanationButton.addClickListener(cl -> {
            Explanation explanation = explanationGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (explanation == null) {
                return;
            }
            explanationService.deleteById(explanation.getId());
            explanationGrid.setItems(explanationService.findAll());
        });

        explanationButtons.add(addExplanationButton, editExplanationButton, deleteExplanationButton);

        componentLayout.add(componentHeader, quocNguGrid, quocNguButtons, explanationGrid, explanationButtons);

        H5 othersHeader = new H5("Ngôn ngữ, Phong cách thư pháp, Nguồn, Chuẩn phồn/giản thể");

        HorizontalLayout languageStyleForm = new HorizontalLayout();
        languageStyleForm.setWidthFull();
        languageStyleForm.setPadding(false);
        languageStyleForm.setMargin(false);

        VerticalLayout languageForm = new VerticalLayout();
        Grid<Language> languageGrid = new Grid<>();
        languageGrid.setHeight("200px");
        languageGrid.addColumn(Language::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        languageGrid.addColumn(Language::getAbbreviation, "abbreviation").setHeader("Tên viết tắt");
        languageGrid.setItems(languageService.findAll());
        HorizontalLayout languageButtons = new HorizontalLayout();
        Button addLanguageButton = new Button("Thêm");
        Button editLanguageButton = new Button("Sửa");
        Button deleteLanguageButton = new Button("Xóa");

        addLanguageButton.addClickListener(cl -> {
            Language newLanguage = Language.builder()
                    .abbreviation("NN")
                    .build();
            languageService.save(newLanguage);
            languageGrid.setItems(languageService.findAll());
        });

        editLanguageButton.addClickListener(cl -> {
            Language language = languageGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (language == null) {
                return;
            }

            // Tạo binder MỚI cho mỗi dialog
            Binder<Language> binder = new Binder<>();
            binder.setBean(language);

            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField abbreviationField = new TextField("Tên viết tắt");
            abbreviationField.setValue(language.getAbbreviation());
            binder.forField(abbreviationField)
                    .asRequired("Vui lòng nhập tên viết tắt")
                    .withValidator(string -> !languageService.existsByAbbreviation(string.trim()) || string.trim().equals(language.getAbbreviation()), "Đã tồn tại ngôn ngữ này")
                    .bind(Language::getAbbreviation, Language::setAbbreviation);
            dialogLayout.add(abbreviationField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    Language editedLanguage = binder.getBean();
                    editedLanguage.setAbbreviation(abbreviationField.getValue().trim());
                    languageService.save(editedLanguage);
                    languageGrid.setItems(languageService.findAll());
                    dialog.close();
                }
            });
            cancelButton.addClickListener(cl2 -> {
                dialog.close();
            });
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteLanguageButton.addClickListener(cl -> {
            Language language = languageGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (language == null) {
                return;
            }
            languageService.deleteById(language.getId());
            languageGrid.setItems(languageService.findAll());
        });

        languageButtons.add(addLanguageButton, editLanguageButton, deleteLanguageButton);
        languageForm.add(languageGrid, languageButtons);

        VerticalLayout styleForm = new VerticalLayout();
        Grid<Style> styleGrid = new Grid<>();
        styleGrid.setHeight("200px");
        styleGrid.addColumn(Style::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        styleGrid.addColumn(Style::getDescription, "description").setHeader("Mô tả phong cách");
        styleGrid.setItems(styleService.findAll());
        HorizontalLayout styleButtons = new HorizontalLayout();
        Button addStyleButton = new Button("Thêm");
        Button editStyleButton = new Button("Sửa");
        Button deleteStyleButton = new Button("Xóa");

        addStyleButton.addClickListener(cl -> {
            Style newStyle = Style.builder()
                    .description("Phong cách mới")
                    .build();
            styleService.save(newStyle);
            styleGrid.setItems(styleService.findAll());
        });

        editStyleButton.addClickListener(cl -> {
            Style style = styleGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (style == null) {
                return;
            }

            // Tạo binder MỚI cho mỗi dialog
            Binder<Style> binder = new Binder<>();
            binder.setBean(style);

            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField meaningField = new TextField("Mô tả phong cách");
            meaningField.setValue(style.getDescription());
            binder.forField(meaningField)
                    .asRequired("Vui lòng nhập mô tả phong cách")
                    .withValidator(string -> !styleService.existsByDescription(string.toLowerCase().trim()) || string.toLowerCase().trim().equals(style.getDescription()), "Đã tồn tại phong cách này")
                    .bind(Style::getDescription, Style::setDescription);
            dialogLayout.add(meaningField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    Style editedStyle = binder.getBean();
                    editedStyle.setDescription(meaningField.getValue().toLowerCase().trim());
                    styleService.save(editedStyle);
                    styleGrid.setItems(styleService.findAll());
                    dialog.close();
                }
            });
            cancelButton.addClickListener(cl2 -> {
                dialog.close();
            });
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteStyleButton.addClickListener(cl -> {
            Style style = styleGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (style == null) {
                return;
            }
            styleService.deleteById(style.getId());
            styleGrid.setItems(styleService.findAll());
        });

        styleButtons.add(addStyleButton, editStyleButton, deleteStyleButton);
        styleForm.add(styleGrid, styleButtons);

        languageStyleForm.add(languageForm, styleForm);
        languageForm.setWidth("60%");
        styleForm.setWidth("40%");

        HorizontalLayout sourceTradSimpForm = new HorizontalLayout();
        sourceTradSimpForm.setWidthFull();

        VerticalLayout sourceForm = new VerticalLayout();
        Grid<Source> sourceGrid = new Grid<>();
//        sourceGrid.setHeight("150px");
        sourceGrid.addColumn(Source::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        sourceGrid.addColumn(Source::getName, "name").setHeader("Tên nguồn");
        sourceGrid.setItems(sourceService.findAll());
        HorizontalLayout sourceButtons = new HorizontalLayout();
        Button addSourceButton = new Button("Thêm");
        Button editSourceButton = new Button("Sửa");
        Button deleteSourceButton = new Button("Xóa");

        addSourceButton.addClickListener(cl -> {
            Source newSource = Source.builder()
                    .name("Nguồn mới")
                    .build();
            sourceService.save(newSource);
            sourceGrid.setItems(sourceService.findAll());
        });

        editSourceButton.addClickListener(cl -> {
            Source source = sourceGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (source == null) {
                return;
            }

            // Tạo binder MỚI cho mỗi dialog
            Binder<Source> binder = new Binder<>();
            binder.setBean(source);

            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField nameField = new TextField("Tên nguồn");
            nameField.setWidth("400px");
            nameField.setValue(source.getName());
            binder.forField(nameField)
                    .asRequired("Vui lòng nhập tên nguồn")
                    .withValidator(string -> !sourceService.existsByName(string.trim()) || string.trim().equals(source.getName()), "Đã tồn tại nguồn này")
                    .bind(Source::getName, Source::setName);
            dialogLayout.add(nameField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    Source editedSource = binder.getBean();
                    editedSource.setName(nameField.getValue().trim());
                    sourceService.save(editedSource);
                    sourceGrid.setItems(sourceService.findAll());
                    dialog.close();
                }
            });
            cancelButton.addClickListener(cl2 -> {
                dialog.close();
            });
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteSourceButton.addClickListener(cl -> {
            Source source = sourceGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (source == null) {
                return;
            }
            sourceService.deleteById(source.getId());
            sourceGrid.setItems(sourceService.findAll());
        });

        sourceButtons.add(addSourceButton, editSourceButton, deleteSourceButton);
        sourceForm.add(sourceGrid, sourceButtons);

        VerticalLayout tradSimpForm = new VerticalLayout();
        Grid<TradSimpStandard> tradSimpStandardGrid = new Grid<>();
//        tradSimpStandardGrid.setHeight("150px");
        tradSimpStandardGrid.addColumn((TradSimpStandard standard) -> standard.getTraditionalCharacter().getString()).setHeader("Phồn thể");
        tradSimpStandardGrid.addColumn((TradSimpStandard standard) -> standard.getSimplifiedCharacter().getString()).setHeader("Giản thể");
        tradSimpStandardGrid.setItems(tradSimpStandardService.findAll());
        HorizontalLayout tradSimpStandardButtons = new HorizontalLayout();
        Button addTradSimpStandardButton = new Button("Thêm");
        Button editTradSimpStandardButton = new Button("Sửa");
        Button deleteTradSimpStandardButton = new Button("Xóa");
        tradSimpStandardButtons.add(addTradSimpStandardButton, editTradSimpStandardButton, deleteTradSimpStandardButton);
        tradSimpForm.add(tradSimpStandardGrid, tradSimpStandardButtons);

        sourceTradSimpForm.add(sourceForm, tradSimpForm);
        sourceTradSimpForm.setWidthFull();
        sourceTradSimpForm.setPadding(false);
        sourceTradSimpForm.setMargin(false);

        sourceForm.setWidth("60%");
        tradSimpForm.setWidth("40%");

        VerticalLayout othersLayout = new VerticalLayout();
        othersLayout.setSizeFull();
        othersLayout.add(othersHeader, languageStyleForm, sourceTradSimpForm);

        layout.add(characterLayout, componentLayout, othersLayout);
        characterLayout.setWidth("30%");
        componentLayout.setWidth("30%");
        othersLayout.setWidth("40%");

        return layout;
    }

    private HorizontalLayout combiningLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();

        // ===== PHẦN CẤU TẠO =====
        VerticalLayout structureLayout = new VerticalLayout();
        structureLayout.setSizeFull();
        H5 structureHeader = new H5("Cấu tạo");

        // Grid hiển thị Structure
        Grid<Structure> structureGrid = new Grid<>();
        structureGrid.addColumn(Structure::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        structureGrid.addColumn(structure -> structure.getCharacter().getString(), "character").setWidth("50px").setFlexGrow(0);
        structureGrid.setItems(structureService.findAll());

        // ===== PHẦN CẤU TẠO CON =====
        H5 structureComponentHeader = new H5("Cấu tạo con");

        // Grid hiển thị StructureComponent (khai báo trước để có thể dùng trong events)
        Grid<StructureComponent> structureComponentGrid = new Grid<>();
        structureComponentGrid.addColumn(sub -> (sub.getStructureComponent().getId() + " - " +
                sub.getStructureComponent().getCharacter().getString()), "character").setWidth("100px").setFlexGrow(0);
        structureComponentGrid.addColumn(sub -> sub.getStructureClassification().getDescription(), "classification").setHeader("Phân loại");
        structureComponentGrid.addColumn(StructureComponent::getQuantity).setHeader("Sl").setWidth("20px").setFlexGrow(0);

        // Nút thêm, sửa, xóa Structure
        HorizontalLayout structureButtons = new HorizontalLayout();
        Button addStructureButton = new Button("Thêm");
        Button editStructureButton = new Button("Sửa");
        Button deleteStructureButton = new Button("Xóa");

        addStructureButton.addClickListener(cl -> {
            Binder<Structure> binder = new Binder();

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm cấu tạo mới");

            VerticalLayout dialogLayout = new VerticalLayout();
            ComboBox<CharacterX> characterField = new ComboBox<>("Ký tự");
            characterField.setItems(characterService.findAll());
            characterField.setItemLabelGenerator(CharacterX::getString);
            characterField.setWidth("300px");
            binder.forField(characterField).asRequired("Vui lòng chọn ký tự").bind(Structure::getCharacter, Structure::setCharacter);

            dialogLayout.add(characterField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    if (characterField.getValue() != null) {
                        Structure newStructure = Structure.builder()
                                .character(characterField.getValue())
                                .structureComponents(new ArrayList<>())
                                .build();
                        structureService.save(newStructure);
                        structureGrid.setItems(structureService.findAll());
                        dialog.close();
                    }
                }
            });

            cancelButton.addClickListener(cl2 -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        editStructureButton.addClickListener(cl -> {
            Structure structure = structureGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (structure == null) {
                return;
            }

            Binder<Structure> binder = new Binder();

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa cấu tạo");

            VerticalLayout dialogLayout = new VerticalLayout();
            ComboBox<CharacterX> characterField = new ComboBox<>("Ký tự");
            characterField.setItems(characterService.findAll());
            characterField.setItemLabelGenerator(CharacterX::getString);
            characterField.setValue(structure.getCharacter());
            characterField.setWidth("300px");
            binder.forField(characterField).asRequired("Vui lòng chọn ký tự").bind(Structure::getCharacter, Structure::setCharacter);

            dialogLayout.add(characterField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    if (characterField.getValue() != null) {
                        structure.setCharacter(characterField.getValue());
                        structureService.save(structure);
                        structureGrid.setItems(structureService.findAll());
                        dialog.close();
                    }
                }
            });

            cancelButton.addClickListener(cl2 -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteStructureButton.addClickListener(cl -> {
            Structure structure = structureGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (structure == null) {
                return;
            }
            structureService.deleteById(structure.getId());
            structureGrid.setItems(structureService.findAll());
            structureComponentGrid.setItems(new ArrayList<>());
        });

        structureButtons.add(addStructureButton, editStructureButton, deleteStructureButton);

        // Nút thêm, sửa, xóa StructureComponent
        HorizontalLayout structureComponentButtons = new HorizontalLayout();
        Button addStructureComponentButton = new Button("Thêm");
        Button editStructureComponentButton = new Button("Sửa");
        Button deleteStructureComponentButton = new Button("Xóa");

        addStructureComponentButton.addClickListener(cl -> {
            Structure structure = structureGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (structure == null) {
                return;
            }

            Binder<StructureComponent> binder = new Binder();

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm cấu tạo con");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Structure> structureComponentField = new ComboBox<>("Cấu tạo con");
            structureComponentField.setItems(structureService.findAll());
            structureComponentField.setItemLabelGenerator(s -> s.getId() + " - " + s.getCharacter().getString());
            structureComponentField.setWidth("300px");
            binder.forField(structureComponentField).asRequired("Vui lòng chọn cấu tạo con").bind(StructureComponent::getStructureComponent, StructureComponent::setStructureComponent);

            ComboBox<StructureClassification> classificationField = new ComboBox<>("Phân loại");
            classificationField.setItems(structureClassificationService.findAll());
            classificationField.setItemLabelGenerator(StructureClassification::getDescription);
            classificationField.setWidth("300px");
            binder.forField(classificationField).asRequired("Vui lòng chọn phân loại").bind(StructureComponent::getStructureClassification, StructureComponent::setStructureClassification);

            IntegerField quantityField = new IntegerField("Số lượng");
            quantityField.setValue(1);
            quantityField.setWidth("300px");
            binder.forField(quantityField).asRequired("Vui lòng nhập số lượng")
                    .withValidator(integer -> integer != null && integer > 0, "Số lượng phải là số nguyên dương")
                    .bind(StructureComponent::getQuantity, StructureComponent::setQuantity);

            dialogLayout.add(structureComponentField, classificationField, quantityField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    if (structureComponentField.getValue() != null && classificationField.getValue() != null) {
                        StructureComponentId structureComponentId = StructureComponentId.builder()
                                .structureId(structure.getId())
                                .structureComponentId(structureComponentField.getValue().getId())
                                .classificationId(classificationField.getValue().getId())
                                .build();

                        StructureComponent newStructureComponent = StructureComponent.builder()
                                .id(structureComponentId)
                                .structure(structure)
                                .structureComponent(structureComponentField.getValue())
                                .structureClassification(classificationField.getValue())
                                .quantity(quantityField.getValue())
                                .build();

                        structureComponentService.save(newStructureComponent);

                        // Refresh structure để lấy structureComponents mới
                        Structure refreshedStructure = structureService.findById(structure.getId());
                        if (refreshedStructure != null) {
                            structureComponentGrid.setItems(refreshedStructure.getStructureComponents());
                        }
                        structureGrid.setItems(structureService.findAll());
                        dialog.close();
                    }
                }
            });

            cancelButton.addClickListener(cl2 -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);

            HorizontalLayout preview = new HorizontalLayout();
            H6 previewHeader = new H6("Xem trước cấu tạo con");
            preview.add(previewHeader);
            dialog.add(dialogButtons, preview);

            structureComponentField.addValueChangeListener(vcl -> {
                Structure selectedStructureComponent = vcl.getValue();
                preview.removeAll();
                preview.add(previewHeader);
                if (selectedStructureComponent != null) {
                    HorizontalLayout structurePreview = visualTool.drawStructure(selectedStructureComponent.getStructureComponents());
                    structurePreview.setSpacing(true);
                    structurePreview.setWidthFull();
                    preview.add(structurePreview);
                }
            });

            dialog.open();
        });

        editStructureComponentButton.addClickListener(cl -> {
            Structure structure = structureGrid.getSelectedItems().stream().findFirst().orElse(null);
            StructureComponent structureComponent = structureComponentGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (structure == null || structureComponent == null) {
                return;
            }

            Binder<StructureComponent> binder = new Binder<>();

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa cấu tạo con");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Structure> structureComponentField = new ComboBox<>("Cấu tạo con");
            structureComponentField.setItems(structureService.findAll());
            structureComponentField.setItemLabelGenerator(s -> s.getId() + " - " + s.getCharacter().getString());
            structureComponentField.setValue(structureComponent.getStructureComponent());
            structureComponentField.setEnabled(false); // Không cho sửa khóa chính
            structureComponentField.setWidth("300px");

            ComboBox<StructureClassification> classificationField = new ComboBox<>("Phân loại");
            classificationField.setItems(structureClassificationService.findAll());
            classificationField.setItemLabelGenerator(StructureClassification::getDescription);
            classificationField.setValue(structureComponent.getStructureClassification());
            classificationField.setEnabled(false); // Không cho sửa khóa chính
            classificationField.setWidth("300px");

            IntegerField quantityField = new IntegerField("Số lượng");
            quantityField.setValue(structureComponent.getQuantity());
            quantityField.setWidth("300px");
            binder.forField(quantityField).asRequired("Vui lòng nhập số lượng")
                    .withValidator(integer -> integer != null && integer > 0, "Số lượng phải là số nguyên dương")
                    .bind(StructureComponent::getQuantity, StructureComponent::setQuantity);

            dialogLayout.add(structureComponentField, classificationField, quantityField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    structureComponent.setQuantity(quantityField.getValue());
                    structureComponentService.save(structureComponent);

                    // Refresh structure để lấy structureComponents mới
                    Structure refreshedStructure = structureService.findById(structure.getId());
                    if (refreshedStructure != null) {
                        structureComponentGrid.setItems(refreshedStructure.getStructureComponents());
                    }
                    dialog.close();
                }
            });

            cancelButton.addClickListener(cl2 -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteStructureComponentButton.addClickListener(cl -> {
            Structure structure = structureGrid.getSelectedItems().stream().findFirst().orElse(null);
            StructureComponent structureComponent = structureComponentGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (structure == null || structureComponent == null) {
                return;
            }

            structureComponentService.deleteById(structureComponent.getId());
            structure.getStructureComponents().remove(structureComponent);
            structureService.save(structure);

            // Refresh structure để lấy structureComponents mới
            Structure refreshedStructure = structureService.findById(structure.getId());
            if (refreshedStructure != null) {
                structureComponentGrid.setItems(refreshedStructure.getStructureComponents());
            }
            structureGrid.setItems(structureService.findAll());
        });

        structureComponentButtons.add(addStructureComponentButton, editStructureComponentButton, deleteStructureComponentButton);

        HorizontalLayout structureAndStructureComponentLayout = new HorizontalLayout();
        VerticalLayout structureSubLayout = new VerticalLayout();
        structureSubLayout.setSpacing(true);
        structureSubLayout.add(structureHeader, structureGrid, structureButtons);
        VerticalLayout structureComponentLayout = new VerticalLayout();
        structureComponentLayout.setSpacing(true);
        structureComponentLayout.add(structureComponentHeader, structureComponentGrid, structureComponentButtons);
        structureAndStructureComponentLayout.add(structureSubLayout, structureComponentLayout);
        structureSubLayout.setWidth("40%");
        structureComponentLayout.setWidth("60%");

        HorizontalLayout structurePreviewLayout = new HorizontalLayout();
        H5 structurePreviewHeader = new H5("Xem trước cấu tạo");
        structurePreviewLayout.add(structurePreviewHeader);
        structureLayout.add(structureAndStructureComponentLayout, structurePreviewLayout);

        // Khi chọn Structure, hiển thị các StructureComponent của nó
        structureGrid.addSelectionListener(selection -> {
            Structure selectedStructure = selection.getFirstSelectedItem().orElse(null);

            structureLayout.removeAll();

            HorizontalLayout newStructurePreview = new HorizontalLayout();
            if (selectedStructure != null) {
                structureComponentGrid.setItems(selectedStructure.getStructureComponents());
                newStructurePreview = visualTool.drawStructure(selectedStructure.getStructureComponents());
            } else {
                structureComponentGrid.setItems(new ArrayList<>());
                newStructurePreview = new HorizontalLayout();
                newStructurePreview.add(structurePreviewHeader);
            }
            newStructurePreview.setSpacing(true);
            newStructurePreview.setWidthFull();
            structureLayout.add(structureAndStructureComponentLayout, newStructurePreview);
        });

        // ===== PHẦN ÂM ĐỌC =====
        VerticalLayout pronunciationLayout = new VerticalLayout();
        pronunciationLayout.setSizeFull();

        VerticalLayout pronunciationSubLayout = new VerticalLayout();
        H5 pronunciationHeader = new H5("Âm đọc");
        Grid<Pronunciation> pronunciationGrid = new Grid<>();
        pronunciationGrid.setHeight("400px");
        pronunciationGrid.addColumn(Pronunciation::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        pronunciationGrid.addColumn(pronunciation -> pronunciation.getQuocNgu().getDescription(), "quocNgu").setHeader("Âm");
        pronunciationGrid.setItems(pronunciationService.findAll());

        HorizontalLayout pronunciationButtons = new HorizontalLayout();
        Button addPronunciationButton = new Button("Thêm");
        Button editPronunciationButton = new Button("Sửa");
        Button deletePronunciationButton = new Button("Xóa");

        addPronunciationButton.addClickListener(cl -> {
            Pronunciation newPronunciation = Pronunciation.builder()
                    .quocNgu(quocNguService.findAll().get(0))
                    .build();
            pronunciationService.save(newPronunciation);
            pronunciationGrid.setItems(pronunciationService.findAll());
        });

        editPronunciationButton.addClickListener(cl -> {
            Pronunciation pronunciation = pronunciationGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (pronunciation == null) {
                return;
            }
            Binder<Pronunciation> binder = new Binder<>();
            binder.setBean(pronunciation);
            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            ComboBox<QuocNgu> quocNguField = new ComboBox<>("Âm đọc");
            quocNguField.setItems(quocNguService.findAll());
            quocNguField.setItemLabelGenerator(QuocNgu::getDescription);
            quocNguField.setValue(pronunciation.getQuocNgu());
            quocNguField.setWidth("400px");
            binder.forField(quocNguField)
                    .asRequired("Vui lòng chọn âm đọc")
                    .bind(Pronunciation::getQuocNgu, Pronunciation::setQuocNgu);
            dialogLayout.add(quocNguField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    Pronunciation editedPronunciation = binder.getBean();
                    pronunciationService.save(editedPronunciation);
                    pronunciationGrid.setItems(pronunciationService.findAll());
                    dialog.close();
                }
            });
            cancelButton.addClickListener(cl2 -> {
                dialog.close();
            });
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deletePronunciationButton.addClickListener(cl -> {
            Pronunciation pronunciation = pronunciationGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (pronunciation == null) {
                return;
            }
            pronunciationService.deleteById(pronunciation.getId());
            pronunciationGrid.setItems(pronunciationService.findAll());
        });

        pronunciationButtons.add(addPronunciationButton, editPronunciationButton, deletePronunciationButton);

        pronunciationSubLayout.add(pronunciationHeader, pronunciationGrid, pronunciationButtons);

        VerticalLayout pronunciationChangeSubLayout = new VerticalLayout();
        H5 pronunciationChangeHeader = new H5("Biến đổi âm đọc");
        Grid<PronunciationEvolution> pronunciationChangeGrid = new Grid<>();
        pronunciationChangeGrid.setHeight("400px");
        pronunciationChangeGrid.addColumn((PronunciationEvolution pronunciationEvolution) ->
                (pronunciationEvolution.getId().getToPronunciationId() + " - " +
                pronunciationEvolution.getToPronunciation().getQuocNgu().getDescription()), "toPronunciation").setHeader("Âm sau");
        pronunciationChangeGrid.addColumn((PronunciationEvolution pronunciationEvolution) ->
                pronunciationEvolution.getPronunciationClassification().getDescription(), "pronunciationClassifcation").setHeader("Phân loại");
        pronunciationChangeGrid.setItems(new ArrayList<>());

        HorizontalLayout pronunciationChangeButtons = new HorizontalLayout();
        Button addPronunciationChangeButton = new Button("Thêm");
        Button deletePronunciationChangeButton = new Button("Xóa");

        addPronunciationChangeButton.addClickListener(cl2 -> {
            Dialog dialog = new Dialog();

            Pronunciation pronunciation = pronunciationGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (pronunciation == null) {
                return;
            }

            Binder<PronunciationEvolution> binder = new Binder<>();
            binder.setBean(new PronunciationEvolution());

            HorizontalLayout pronunciationChangeForm = new HorizontalLayout();

            ComboBox<Pronunciation> toPronunciationField = new ComboBox("Âm sau");
            toPronunciationField.setItems(pronunciationService.findAll());
            toPronunciationField.setItemLabelGenerator((Pronunciation p) -> p.getQuocNgu().getDescription());
            toPronunciationField.setValue(binder.getBean().getFromPronunciation());
            toPronunciationField.setValue(binder.getBean().getFromPronunciation());

            ComboBox<PronunciationClassification> classificationField = new ComboBox<>("Phân loại");
            classificationField.setItems(pronunciationClassificationService.findAll());
            classificationField.setItemLabelGenerator(PronunciationClassification::getDescription);
            classificationField.setValue(binder.getBean().getPronunciationClassification());

            binder.forField(toPronunciationField).asRequired("Vui lòng chọn âm sau")
                    .withValidator((p) -> pronunciationEvolutionService.findById(new PronunciationEvolutionId().builder()
                            .fromPronunciationId(pronunciation.getId())
                            .toPronunciationId(toPronunciationField.getValue() != null ? toPronunciationField.getValue().getId() : null)
                            .pronunciationClassificationId(classificationField.getValue() != null ? classificationField.getValue().getId() : null)
                            .build()) == null, "Biến đổi âm đọc này đã tồn tại")
                    .bind(PronunciationEvolution::getToPronunciation, PronunciationEvolution::setToPronunciation);

            binder.forField(classificationField).asRequired("Vui lòng chọn phân loại")
                    .withValidator((p) -> pronunciationEvolutionService.findById(new PronunciationEvolutionId().builder()
                            .fromPronunciationId(pronunciation.getId())
                            .toPronunciationId(toPronunciationField.getValue() != null ? toPronunciationField.getValue().getId() : null)
                            .pronunciationClassificationId(classificationField.getValue() != null ? classificationField.getValue().getId() : null)
                            .build()) == null, "Biến đổi âm đọc này đã tồn tại")
                    .bind(PronunciationEvolution::getPronunciationClassification, PronunciationEvolution::setPronunciationClassification);

            pronunciationChangeForm.add(toPronunciationField, classificationField);

            HorizontalLayout pronunciationChangeFormButton = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl3 -> {
                if (binder.validate().isOk()) {
                    PronunciationEvolution editedPronunciationEvolution = PronunciationEvolution.builder()
                            .id(new PronunciationEvolutionId().builder()
                                    .fromPronunciationId(pronunciation.getId())
                                    .toPronunciationId(toPronunciationField.getValue().getId())
                                    .pronunciationClassificationId(classificationField.getValue().getId())
                                    .build())
                            .toPronunciation(toPronunciationField.getValue())
                            .fromPronunciation(pronunciation)
                            .pronunciationClassification(classificationField.getValue())
                            .build();

                pronunciationEvolutionService.save(editedPronunciationEvolution);
                pronunciationChangeGrid.setItems(new ArrayList<>());
                dialog.close();
                }
            });

            cancelButton.addClickListener(cl3 -> {
                dialog.close();
            });

            pronunciationChangeFormButton.add(saveButton, cancelButton);

            VerticalLayout pronunciationChangePreview = new VerticalLayout();
            H5 pronunciationChangePreviewHeader = new H5("Xem trước biến đổi âm đọc");
            pronunciationChangePreview.add(pronunciationChangePreviewHeader);

            toPronunciationField.addValueChangeListener(vcl -> {
                pronunciationChangePreview.removeAll();
                pronunciationChangePreview.add(pronunciationChangePreviewHeader);
                Pronunciation selectedPreviousPronunciation = vcl.getValue();
                if (selectedPreviousPronunciation != null) {
                    pronunciationChangePreview.add(visualTool.drawPronunciation(selectedPreviousPronunciation));
                }
            });

            dialog.add(pronunciationChangeForm, pronunciationChangeFormButton, pronunciationChangePreview);
            dialog.open();
        });

        deletePronunciationChangeButton.addClickListener(cl2 -> {
            PronunciationEvolution pronunciationEvolution = pronunciationChangeGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (pronunciationEvolution == null) {
                return;
            }
            pronunciationEvolutionService.delete(pronunciationEvolution.getId());
            pronunciationChangeGrid.setItems(new ArrayList<>());
        });
        pronunciationChangeButtons.add(addPronunciationChangeButton, deletePronunciationChangeButton);

        pronunciationChangeSubLayout.add(pronunciationChangeHeader, pronunciationChangeGrid, pronunciationChangeButtons);

        HorizontalLayout pronunciationAndChangeLayout = new HorizontalLayout();
        pronunciationAndChangeLayout.add(pronunciationSubLayout, pronunciationChangeSubLayout);
        pronunciationSubLayout.getStyle().setWidth("40%");
        pronunciationChangeSubLayout.getStyle().setWidth("60%");


        VerticalLayout pronunciationChangePreview = new VerticalLayout();
        H5 pronunciationChangePreviewHeader = new H5("Xem trước biến đổi âm đọc");
        pronunciationChangePreview.add(pronunciationChangePreviewHeader);

        pronunciationLayout.add(pronunciationAndChangeLayout, pronunciationChangePreview);

        pronunciationGrid.addSelectionListener(selectionEvent -> {
            pronunciationChangePreview.removeAll();
            Pronunciation selectedPronunciation = pronunciationGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (selectedPronunciation == null) {
                pronunciationChangePreview.add(pronunciationChangePreviewHeader);
                pronunciationChangeGrid.setItems(new ArrayList<>());
                return;
            }
            List<PronunciationEvolution> pronunciationEvolutions = pronunciationEvolutionService.findByFromPronunciation(selectedPronunciation);
            pronunciationChangeGrid.setItems(pronunciationEvolutions);
            pronunciationChangePreview.add(visualTool.drawPronunciation(selectedPronunciation));
        });


        VerticalLayout meaningLayout = new VerticalLayout();
        meaningLayout.setSizeFull();
        H5 meaningHeader = new H5("Nghĩa");

        // Bảng Meaning
        VerticalLayout meaningSubLayout = new VerticalLayout();
        Grid<Meaning> meaningGrid = new Grid<>();
//        meaningGrid.setHeight("300px");
        meaningGrid.addColumn(Meaning::getId).setHeader("Mã").setWidth("75px").setFlexGrow(0);
        meaningGrid.setItems(meaningService.findAll());

        HorizontalLayout meaningButtons = new HorizontalLayout();
        Button addMeaningButton = new Button("Thêm");
        Button deleteMeaningButton = new Button("Xóa");

        // Bảng Explanation
        VerticalLayout explanationSubLayout = new VerticalLayout();
        H5 explanationHeader = new H5("Giải nghĩa");

        Grid<Explanation> explanationGrid = new Grid<>();
//        explanationGrid.setHeight("300px");
        explanationGrid.addColumn(Explanation::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        explanationGrid.addColumn(Explanation::getDescription).setHeader("Mô tả");
        explanationGrid.setItems(new ArrayList<>());

        HorizontalLayout explanationButtons = new HorizontalLayout();
        Button addExplanationButton = new Button("Thêm");
        Button editExplanationButton = new Button("Sửa");
        Button deleteExplanationButton = new Button("Xóa");

        // Events cho Meaning
        addMeaningButton.addClickListener(cl -> {
            Meaning newMeaning = Meaning.builder()
                    .explanations(new ArrayList<>())
                    .build();
            meaningService.save(newMeaning);
            meaningGrid.setItems(meaningService.findAll());
        });

        deleteMeaningButton.addClickListener(cl -> {
            Meaning meaning = meaningGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (meaning == null) {
                return;
            }
            meaningService.deleteById(meaning.getId());
            meaningGrid.setItems(meaningService.findAll());
            explanationGrid.setItems(new ArrayList<>());
        });

        meaningButtons.add(addMeaningButton, deleteMeaningButton);
        meaningSubLayout.add(meaningHeader, meaningGrid, meaningButtons);

        // Events cho Explanation
        addExplanationButton.addClickListener(cl -> {
            Meaning meaning = meaningGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (meaning == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm giải nghĩa");

            VerticalLayout dialogLayout = new VerticalLayout();
            ComboBox<Explanation> explanationComboBox = new ComboBox<>("Chọn giải nghĩa");
            explanationComboBox.setItems(explanationService.findAll());
            explanationComboBox.setItemLabelGenerator(Explanation::getDescription);
            explanationComboBox.setWidth("400px");

            dialogLayout.add(explanationComboBox);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(cl2 -> {
                if (explanationComboBox.getValue() != null) {
                    Explanation selectedExplanation = explanationComboBox.getValue();
                    // Kiểm tra xem giải nghĩa này đã thuộc về meaning hiện tại chưa
                    if (meaning.getExplanations().contains(selectedExplanation)) {
                        // Thông báo lỗi
                        dialog.close();
                        Dialog errorDialog = new Dialog();
                        errorDialog.add(new Span("Giải nghĩa này đã thuộc về ý nghĩa hiện tại!"));
                        Button okButton = new Button("OK", e -> errorDialog.close());
                        errorDialog.add(okButton);
                        errorDialog.open();
                        return;
                    }
                    // Thêm giải nghĩa vào meaning
                    meaning.getExplanations().add(selectedExplanation);
                    meaningService.save(meaning);
                    explanationGrid.setItems(meaning.getExplanations());
                    dialog.close();
                }
            });

            cancelButton.addClickListener(cl2 -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        editExplanationButton.addClickListener(cl -> {
            Meaning meaning = meaningGrid.getSelectedItems().stream().findFirst().orElse(null);
            Explanation explanation = explanationGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (meaning == null || explanation == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa giải nghĩa");

            VerticalLayout dialogLayout = new VerticalLayout();
            ComboBox<Explanation> explanationComboBox = new ComboBox<>("Chọn giải nghĩa thay thế");
            explanationComboBox.setItems(explanationService.findAll());
            explanationComboBox.setItemLabelGenerator(Explanation::getDescription);
            explanationComboBox.setValue(explanation);
            explanationComboBox.setWidth("400px");

            dialogLayout.add(explanationComboBox);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(cl2 -> {
                if (explanationComboBox.getValue() != null) {
                    Explanation newExplanation = explanationComboBox.getValue();

                    // Nếu chọn giải nghĩa khác với giải nghĩa hiện tại
                    if (!newExplanation.getId().equals(explanation.getId())) {
                        // Kiểm tra xem giải nghĩa mới đã thuộc về meaning hiện tại chưa
                        if (meaning.getExplanations().contains(newExplanation)) {
                            dialog.close();
                            Dialog errorDialog = new Dialog();
                            errorDialog.add(new Span("Giải nghĩa này đã thuộc về ý nghĩa hiện tại!"));
                            Button okButton = new Button("OK", e -> errorDialog.close());
                            errorDialog.add(okButton);
                            errorDialog.open();
                            return;
                        }

                        // Gỡ giải nghĩa cũ khỏi meaning
                        meaning.getExplanations().remove(explanation);

                        // Thêm giải nghĩa mới vào meaning
                        meaning.getExplanations().add(newExplanation);
                        meaningService.save(meaning);
                    }

                    explanationGrid.setItems(meaning.getExplanations());
                    dialog.close();
                }
            });

            cancelButton.addClickListener(cl2 -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteExplanationButton.addClickListener(cl -> {
            Meaning meaning = meaningGrid.getSelectedItems().stream().findFirst().orElse(null);
            Explanation explanation = explanationGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (meaning == null || explanation == null) {
                return;
            }
            // Gỡ giải nghĩa khỏi meaning (không xóa giải nghĩa)
            meaning.getExplanations().remove(explanation);
            meaningService.save(meaning);
            explanationGrid.setItems(meaning.getExplanations());
        });

        explanationButtons.add(addExplanationButton, editExplanationButton, deleteExplanationButton);
        explanationSubLayout.add(explanationHeader, explanationGrid, explanationButtons);

        // Khi chọn Meaning, hiển thị các Explanation của nó
        meaningGrid.addSelectionListener(selection -> {
            Meaning selectedMeaning = selection.getFirstSelectedItem().orElse(null);
            if (selectedMeaning != null) {
                explanationGrid.setItems(explanationService.findByMeaning(selectedMeaning));
            } else {
                explanationGrid.setItems(new ArrayList<>());
            }
        });

        HorizontalLayout meaningAndExplanationLayout = new HorizontalLayout();
        meaningAndExplanationLayout.add(meaningSubLayout, explanationSubLayout);
        meaningSubLayout.setWidth("40%");
        explanationSubLayout.setWidth("60%");

        meaningLayout.add(meaningAndExplanationLayout);

        layout.add(structureLayout, pronunciationLayout, meaningLayout);
        structureLayout.setWidth("35%");
        pronunciationLayout.setWidth("35%");
        meaningLayout.setWidth("30%");

        return layout;
    }

    private HorizontalLayout entitiesLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();

        VerticalLayout leftLayout = new VerticalLayout();
        leftLayout.setSizeFull();
        H5 entityHeader = new H5("Thực thể");

        Grid<EntityX> entityGrid = new Grid<>();
        entityGrid.setHeight("400px");
        entityGrid.addColumn(EntityX::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        entityGrid.addColumn(entity -> entity.getCharacterString()).setHeader("Ký tự").setWidth("60px");
        entityGrid.addColumn(entity -> entity.getStructureId()).setHeader("Cấu tạo").setWidth("100px");
        entityGrid.addColumn(entity -> entity.getPronunciationString(), "pronunciation").setHeader("Âm đọc").setWidth("120px");
        entityGrid.addColumn(entity -> {
            StringBuilder meanings = new StringBuilder();
            for (Explanation explanation : entity.getMeaning().getExplanations()) {
                meanings.append(explanation.getDescription()).append("; ");
            }
            return meanings.toString();
        }).setHeader("Nghĩa");
        entityGrid.addColumn(entity -> entity.getLanguage().getAbbreviation(), "language").setHeader("Ngôn ngữ").setWidth("100px");
        entityGrid.addColumn(EntityX::isCompound).setHeader("Phức hợp").setWidth("100px");
        entityGrid.addColumn(EntityX::isAttested).setHeader("Có chứng thực").setWidth("100px");
        entityGrid.addColumn(EntityX::isStandardised).setHeader("Chuẩn hóa").setWidth("100px");
        entityGrid.addColumn(EntityX::getDescription).setHeader("Mô tả");
        entityGrid.setItems(entityService.findAll());

        HorizontalLayout entityButtons = new HorizontalLayout();
        Button addEntityButton = new Button("Thêm");
        Button editEntityButton = new Button("Sửa");
        Button deleteEntityButton = new Button("Xóa");

        entityButtons.add(addEntityButton, editEntityButton, deleteEntityButton);

        leftLayout.add(entityHeader, entityGrid, entityButtons);

        // ===== PHẦN THỰC THỂ PHỨ HỢP =====
        VerticalLayout rightLayout = new VerticalLayout();
        rightLayout.setSizeFull();

        H5 compositionHeader = new H5("Thực thể phức hợp");

        // Bảng hiển thị parent entity
        VerticalLayout parentEntityLayout = new VerticalLayout();
        H6 parentEntityHeader = new H6("Thực thể cha");

        Grid<EntityX> parentEntityGrid = new Grid<>();
        parentEntityGrid.setHeight("150px");
        parentEntityGrid.addColumn(EntityX::getId).setHeader("Mã").setWidth("75px").setFlexGrow(0);
        parentEntityGrid.addColumn(e -> e.getCharacterString()).setHeader("Ký tự");
        parentEntityGrid.addColumn(e -> e.getPronunciationString()).setHeader("Âm đọc");
        parentEntityGrid.setItems(entityService.findByCompound(true));

        parentEntityLayout.add(parentEntityHeader, parentEntityGrid);

        // Bảng hiển thị child entity và position
        VerticalLayout childEntityLayout = new VerticalLayout();
        H6 childEntityHeader = new H6("Thành phần thực thể con");

        Grid<EntityComposition> compositionGrid = new Grid<>();
        compositionGrid.setHeight("300px");
        compositionGrid.addColumn(ec -> ec.getChildEntity().getId()).setHeader("Mã").setWidth("100px");
        compositionGrid.addColumn(ec -> ec.getChildEntity().getStructure().getCharacter().getString()).setHeader("Ký tự").setWidth("60px");
        compositionGrid.addColumn(ec -> ec.getId().getPosition()).setHeader("Vị trí").setWidth("75px");
        compositionGrid.setItems(new ArrayList<>());

        HorizontalLayout compositionButtons = new HorizontalLayout();
        Button addCompositionButton = new Button("Thêm");
        Button editCompositionButton = new Button("Sửa");
        Button deleteCompositionButton = new Button("Xóa");

        // Thêm thành phần thực thể con
        addCompositionButton.addClickListener(cl -> {
            EntityX parentEntity = parentEntityGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (parentEntity == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm thành phần thực thể con");
            dialog.setWidth("500px");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<EntityX> childEntityField = new ComboBox<>("Thực thể con");
            childEntityField.setItems(entityService.findAll());
            childEntityField.setItemLabelGenerator(e -> e.getId() + " - " + e.getCharacterString() + " (" + e.getPronunciationString() + ")"
                + (e.isStandardised() ? " *" : ""));
            childEntityField.setWidth("100%");

            IntegerField positionField = new IntegerField("Vị trí");
            positionField.setValue(1);
            positionField.setWidth("100%");

            dialogLayout.add(childEntityField, positionField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                if (childEntityField.getValue() != null && positionField.getValue() != null) {
                    EntityCompositionId compositionId = EntityCompositionId.builder()
                            .parentEntityId(parentEntity.getId())
                            .childEntityId(childEntityField.getValue().getId())
                            .position(positionField.getValue().longValue())
                            .build();

                    EntityComposition composition = EntityComposition.builder()
                            .id(compositionId)
                            .parentEntity(parentEntity)
                            .childEntity(childEntityField.getValue())
                            .build();

                    entityCompositionService.save(composition);
                    compositionGrid.setItems(entityCompositionService.findByParentEntityId(parentEntity.getId()));
                    dialog.close();
                }
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Sửa thành phần thực thể con (chỉ có thể sửa position)
        editCompositionButton.addClickListener(cl -> {
            EntityX parentEntity = parentEntityGrid.getSelectedItems().stream().findFirst().orElse(null);
            EntityComposition composition = compositionGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (parentEntity == null || composition == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa vị trí thực thể con");
            dialog.setWidth("500px");

            VerticalLayout dialogLayout = new VerticalLayout();

            TextField childEntityField = new TextField("Thực thể con");
            childEntityField.setValue(composition.getChildEntity().getId() + " - " +
                    composition.getChildEntity().getStructure().getCharacter().getString());
            childEntityField.setEnabled(false);
            childEntityField.setWidth("100%");

            IntegerField positionField = new IntegerField("Vị trí");
            positionField.setValue(composition.getId().getPosition().intValue());
            positionField.setWidth("100%");

            dialogLayout.add(childEntityField, positionField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                if (positionField.getValue() != null) {
                    // Xóa composition cũ
                    entityCompositionService.deleteById(composition.getId());

                    // Tạo composition mới với position mới
                    EntityCompositionId newId = EntityCompositionId.builder()
                            .parentEntityId(parentEntity.getId())
                            .childEntityId(composition.getChildEntity().getId())
                            .position(positionField.getValue().longValue())
                            .build();

                    EntityComposition newComposition = EntityComposition.builder()
                            .id(newId)
                            .parentEntity(parentEntity)
                            .childEntity(composition.getChildEntity())
                            .build();

                    entityCompositionService.save(newComposition);
                    compositionGrid.setItems(entityCompositionService.findByParentEntityId(parentEntity.getId()));
                    dialog.close();
                }
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Xóa thành phần thực thể con
        deleteCompositionButton.addClickListener(cl -> {
            EntityComposition composition = compositionGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (composition == null) {
                return;
            }

            entityCompositionService.deleteById(composition.getId());
            EntityX parentEntity = parentEntityGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (parentEntity != null) {
                compositionGrid.setItems(entityCompositionService.findByParentEntityId(parentEntity.getId()));
            }
        });

        compositionButtons.add(addCompositionButton, editCompositionButton, deleteCompositionButton);
        childEntityLayout.add(childEntityHeader, compositionGrid, compositionButtons);

        rightLayout.add(compositionHeader, parentEntityLayout, childEntityLayout);

        // Sự kiện khi chọn parent entity, hiển thị các thành phần con
        parentEntityGrid.addSelectionListener(selection -> {
            EntityX selectedParentEntity = selection.getFirstSelectedItem().orElse(null);
            if (selectedParentEntity != null) {
                List<EntityComposition> compositions = entityCompositionService.findByParentEntityId(selectedParentEntity.getId());
                compositionGrid.setItems(compositions);
            } else {
                compositionGrid.setItems(new ArrayList<>());
            }
        });

        // Thêm thực thể mới
        addEntityButton.addClickListener(cl -> {
            Binder<EntityX> binder = new Binder<>();
            EntityX newEntity = new EntityX();
            binder.setBean(newEntity);

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm thực thể mới");
            dialog.setWidth("600px");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Structure> structureField = new ComboBox<>("Cấu tạo");
            structureField.setItems(structureService.findAll());
            structureField.setItemLabelGenerator(s -> s.getId() + " - " + s.getCharacter().getString());
            structureField.setWidth("100%");
            binder.forField(structureField)
//                    .asRequired("Vui lòng chọn cấu tạo")
                    .bind(EntityX::getStructure, EntityX::setStructure);

            ComboBox<Pronunciation> pronunciationField = new ComboBox<>("Âm đọc");
            pronunciationField.setItems(pronunciationService.findAll());
            pronunciationField.setItemLabelGenerator(p -> p.getId() + " - " + p.getQuocNgu().getDescription());
            pronunciationField.setWidth("100%");
            binder.forField(pronunciationField)
//                    .asRequired("Vui lòng chọn âm đọc")
                    .bind(EntityX::getPronunciation, EntityX::setPronunciation);

            ComboBox<Meaning> meaningField = new ComboBox<>("Ý nghĩa");
            meaningField.setItems(meaningService.findAll());
            meaningField.setItemLabelGenerator(m -> {
                StringBuilder sb = new StringBuilder(m.getId() + " - ");
                m.getExplanations().forEach(e -> sb.append(e.getDescription()).append("; "));
                return sb.toString();
            });
            meaningField.setWidth("100%");
            binder.forField(meaningField)
                    .asRequired("Vui lòng chọn ý nghĩa")
                    .bind(EntityX::getMeaning, EntityX::setMeaning);

            ComboBox<Language> languageField = new ComboBox<>("Ngôn ngữ");
            languageField.setItems(languageService.findAll());
            languageField.setItemLabelGenerator(l -> l.getId() + " - " + l.getAbbreviation());
            languageField.setWidth("100%");
            binder.forField(languageField)
                    .asRequired("Vui lòng chọn ngôn ngữ")
                    .bind(EntityX::getLanguage, EntityX::setLanguage);

            TextField descriptionField = new TextField("Mô tả");
            descriptionField.setWidth("100%");
            binder.forField(descriptionField)
                    .bind(EntityX::getDescription, EntityX::setDescription);

            Checkbox compoundCheckbox = new Checkbox("Phức hợp");
            binder.forField(compoundCheckbox)
                    .bind(EntityX::isCompound, EntityX::setCompound);

            Checkbox attestedCheckbox = new Checkbox("Có chứng thực");
            binder.forField(attestedCheckbox)
                    .bind(EntityX::isAttested, EntityX::setAttested);

            Checkbox standardisedCheckbox = new Checkbox("Chuẩn hóa");
            binder.forField(standardisedCheckbox)
                    .bind(EntityX::isStandardised, EntityX::setStandardised);

            dialogLayout.add(structureField, pronunciationField, meaningField, languageField,
                    descriptionField, compoundCheckbox, attestedCheckbox, standardisedCheckbox);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            pronunciationField.addValueChangeListener(listener -> {
                Meaning selectedMeaning = meaningField.getValue();
                meaningField.setItems(meaningService.findAllWithPronunciation(pronunciationField.getValue()));
                meaningField.setValue(selectedMeaning);
            });

            saveButton.addClickListener(e -> {
                if (binder.validate().isOk()) {
                    entityService.save(newEntity);
                    entityGrid.setItems(entityService.findAll());
                    parentEntityGrid.setItems(entityService.findByCompound(true));
                    dialog.close();
                }
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Sửa thực thể
        editEntityButton.addClickListener(cl -> {
            EntityX entity = entityGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (entity == null) {
                return;
            }

            Binder<EntityX> binder = new Binder<>();
            binder.setBean(entity);

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa thực thể");
            dialog.setWidth("600px");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Structure> structureField = new ComboBox<>("Cấu tạo");
            structureField.setItems(structureService.findAll());
            structureField.setItemLabelGenerator(s -> s.getId() + " - " + s.getCharacter().getString());
            structureField.setValue(entity.getStructure());
            structureField.setWidth("100%");
            binder.forField(structureField)
//                    .asRequired("Vui lòng chọn cấu tạo")
                    .bind(EntityX::getStructure, EntityX::setStructure);

            ComboBox<Pronunciation> pronunciationField = new ComboBox<>("Âm đọc");
            pronunciationField.setItems(pronunciationService.findAll());
            pronunciationField.setItemLabelGenerator(p -> p.getId() + " - " + p.getQuocNgu().getDescription());
            pronunciationField.setValue(entity.getPronunciation());
            pronunciationField.setWidth("100%");
            binder.forField(pronunciationField)
//                    .asRequired("Vui lòng chọn âm đọc")
                    .bind(EntityX::getPronunciation, EntityX::setPronunciation);

            ComboBox<Meaning> meaningField = new ComboBox<>("Ý nghĩa");
            meaningField.setItems(meaningService.findAllWithPronunciation(pronunciationField.getValue()));
            meaningField.setItemLabelGenerator(m -> {
                StringBuilder sb = new StringBuilder(m.getId() + " - ");
                m.getExplanations().forEach(e -> sb.append(e.getDescription()).append("; "));
                return sb.toString();
            });
            meaningField.setValue(entity.getMeaning());
            meaningField.setWidth("100%");
            binder.forField(meaningField)
                    .asRequired("Vui lòng chọn ý nghĩa")
                    .bind(EntityX::getMeaning, EntityX::setMeaning);

            ComboBox<Language> languageField = new ComboBox<>("Ngôn ngữ");
            languageField.setItems(languageService.findAll());
            languageField.setItemLabelGenerator(l -> l.getId() + " - " + l.getAbbreviation());
            languageField.setValue(entity.getLanguage());
            languageField.setWidth("100%");
            binder.forField(languageField)
                    .asRequired("Vui lòng chọn ngôn ngữ")
                    .bind(EntityX::getLanguage, EntityX::setLanguage);

            TextField descriptionField = new TextField("Mô tả");
            descriptionField.setValue(entity.getDescription() != null ? entity.getDescription() : "");
            descriptionField.setWidth("100%");
            binder.forField(descriptionField)
                    .bind(EntityX::getDescription, EntityX::setDescription);

            Checkbox compoundCheckbox = new Checkbox("Phức hợp");
            compoundCheckbox.setValue(entity.isCompound());
            binder.forField(compoundCheckbox)
                    .bind(EntityX::isCompound, EntityX::setCompound);

            Checkbox attestedCheckbox = new Checkbox("Có chứng thực");
            attestedCheckbox.setValue(entity.isAttested());
            binder.forField(attestedCheckbox)
                    .bind(EntityX::isAttested, EntityX::setAttested);

            Checkbox standardisedCheckbox = new Checkbox("Chuẩn hóa");
            binder.forField(standardisedCheckbox)
                    .bind(EntityX::isStandardised, EntityX::setStandardised);

            dialogLayout.add(structureField, pronunciationField, meaningField, languageField,
                    descriptionField, compoundCheckbox, attestedCheckbox, standardisedCheckbox);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            pronunciationField.addValueChangeListener(listener -> {
                Meaning selectedMeaning =meaningField.getValue();
                meaningField.setItems(meaningService.findAllWithPronunciation(pronunciationField.getValue()));
                meaningField.setValue(selectedMeaning);
            });

            saveButton.addClickListener(e -> {
                if (binder.validate().isOk()) {
                    entityService.save(entity);
                    entityGrid.setItems(entityService.findAll());
                    dialog.close();
                }
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Xóa thực thể
        deleteEntityButton.addClickListener(cl -> {
            EntityX entity = entityGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (entity == null) {
                return;
            }
            entityService.deleteById(entity.getId());
            entityGrid.setItems(entityService.findAll());
            parentEntityGrid.setItems(entityService.findByCompound(true));
        });

        // ===== PHẦN PHÁT TRIỂN THỰC THỂ (ENTITY EVOLUTION) =====
        VerticalLayout entityEvolutionLayout = new VerticalLayout();
        entityEvolutionLayout.setWidthFull();

        H5 entityEvolutionHeader = new H5("Phát triển thực thể");

        Grid<EntityEvolution> entityEvolutionGrid = new Grid<>();
        entityEvolutionGrid.setHeight("300px");
        entityEvolutionGrid.addColumn(ee -> ee.getFromEntity().getId()).setHeader("Thực thể nguồn").setWidth("120px");
        entityEvolutionGrid.addColumn(ee -> ee.getToEntity().getId()).setHeader("Thực thể đích").setWidth("120px");
        entityEvolutionGrid.addColumn(EntityEvolution::getDescription).setHeader("Mô tả");
        entityEvolutionGrid.setItems(entityEvolutionService.findAll());

        HorizontalLayout entityEvolutionButtons = new HorizontalLayout();
        Button addEntityEvolutionButton = new Button("Thêm");
        Button editEntityEvolutionButton = new Button("Sửa");
        Button deleteEntityEvolutionButton = new Button("Xóa");

        // Thêm quan hệ phát triển thực thể
        addEntityEvolutionButton.addClickListener(cl -> {
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm quan hệ phát triển thực thể");
            dialog.setWidth("600px");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<EntityX> fromEntityField = new ComboBox<>("Thực thể nguồn");
            fromEntityField.setItems(entityService.findAll());
            fromEntityField.setItemLabelGenerator(e -> e.getId() + " - " + e.getCharacterString() + " (" + e.getPronunciationString() + ")");
            fromEntityField.setWidth("100%");

            ComboBox<EntityX> toEntityField = new ComboBox<>("Thực thể đích");
            toEntityField.setItems(entityService.findAll());
            toEntityField.setItemLabelGenerator(e -> e.getId() + " - " + e.getCharacterString() + " (" + e.getPronunciationString() + ")");
            toEntityField.setWidth("100%");

            TextField descriptionField = new TextField("Mô tả");
            descriptionField.setWidth("100%");

            dialogLayout.add(fromEntityField, toEntityField, descriptionField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                if (fromEntityField.getValue() != null && toEntityField.getValue() != null) {
                    EntityEvolutionId id = new EntityEvolutionId(
                        fromEntityField.getValue().getId(),
                        toEntityField.getValue().getId()
                    );

                    EntityEvolution evolution = EntityEvolution.builder()
                        .id(id)
                        .fromEntity(fromEntityField.getValue())
                        .toEntity(toEntityField.getValue())
                        .description(descriptionField.getValue())
                        .build();

                    entityEvolutionService.save(evolution);
                    entityEvolutionGrid.setItems(entityEvolutionService.findAll());
                    dialog.close();
                }
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Sửa quan hệ phát triển thực thể (chỉ có thể sửa mô tả)
        editEntityEvolutionButton.addClickListener(cl -> {
            EntityEvolution evolution = entityEvolutionGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (evolution == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa mô tả quan hệ phát triển");
            dialog.setWidth("600px");

            VerticalLayout dialogLayout = new VerticalLayout();

            TextField fromEntityField = new TextField("Thực thể nguồn");
            fromEntityField.setValue(evolution.getFromEntity().getId() + " - " +
                evolution.getFromEntity().getStructure().getCharacter().getString());
            fromEntityField.setEnabled(false);
            fromEntityField.setWidth("100%");

            TextField toEntityField = new TextField("Thực thể đích");
            toEntityField.setValue(evolution.getToEntity().getId() + " - " +
                evolution.getToEntity().getStructure().getCharacter().getString());
            toEntityField.setEnabled(false);
            toEntityField.setWidth("100%");

            TextField descriptionField = new TextField("Mô tả");
            descriptionField.setValue(evolution.getDescription() != null ? evolution.getDescription() : "");
            descriptionField.setWidth("100%");

            dialogLayout.add(fromEntityField, toEntityField, descriptionField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                evolution.setDescription(descriptionField.getValue());
                entityEvolutionService.save(evolution);
                entityEvolutionGrid.setItems(entityEvolutionService.findAll());
                dialog.close();
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Xóa quan hệ phát triển thực thể
        deleteEntityEvolutionButton.addClickListener(cl -> {
            EntityEvolution evolution = entityEvolutionGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (evolution == null) {
                return;
            }
            entityEvolutionService.deleteById(evolution.getId());
            entityEvolutionGrid.setItems(entityEvolutionService.findAll());
        });

        entityEvolutionButtons.add(addEntityEvolutionButton, editEntityEvolutionButton, deleteEntityEvolutionButton);
        entityEvolutionLayout.add(entityEvolutionHeader, entityEvolutionGrid, entityEvolutionButtons);

        layout.add(leftLayout, rightLayout, entityEvolutionLayout);
        leftLayout.setWidth("50%");
        rightLayout.setWidth("20%");
        entityEvolutionLayout.setWidth("30%");

        return layout;
    }

    private HorizontalLayout complementaryLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();

        // ===== BẢNG VÍ DỤ =====
        VerticalLayout exampleLayout = new VerticalLayout();
        exampleLayout.setSizeFull();
        H5 exampleHeader = new H5("Ví dụ");

        Grid<Example> exampleGrid = new Grid<>();
        exampleGrid.setHeight("300px");
        exampleGrid.addColumn(Example::getId).setHeader("Mã").setWidth("75px").setFlexGrow(0);
        exampleGrid.addColumn(e -> e.getSource() != null ? e.getSource().getName() : "").setHeader("Nguồn");
        exampleGrid.setItems(exampleService.findAll());

        HorizontalLayout exampleButtons = new HorizontalLayout();
        Button addExampleButton = new Button("Thêm");
        Button editExampleButton = new Button("Sửa");
        Button deleteExampleButton = new Button("Xóa");

        // Thêm ví dụ mới
        addExampleButton.addClickListener(cl -> {
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm ví dụ mới");
            dialog.setWidth("600px");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Source> sourceField = new ComboBox<>("Nguồn");
            sourceField.setItems(sourceService.findAll());
            sourceField.setItemLabelGenerator(Source::getName);
            sourceField.setWidth("100%");

            dialogLayout.add(sourceField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                Example newExample = Example.builder()
                        .source(sourceField.getValue())
                        .build();
                exampleService.save(newExample);
                exampleGrid.setItems(exampleService.findAll());
                dialog.close();
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Sửa ví dụ
        editExampleButton.addClickListener(cl -> {
            Example example = exampleGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (example == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa ví dụ");
            dialog.setWidth("600px");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Source> sourceField = new ComboBox<>("Nguồn");
            sourceField.setItems(sourceService.findAll());
            sourceField.setItemLabelGenerator(Source::getName);
            sourceField.setValue(example.getSource());
            sourceField.setWidth("100%");

            dialogLayout.add(sourceField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                example.setSource(sourceField.getValue());
                exampleService.save(example);
                exampleGrid.setItems(exampleService.findAll());
                dialog.close();
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Xóa ví dụ
        deleteExampleButton.addClickListener(cl -> {
            Example example = exampleGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (example == null) {
                return;
            }
            exampleService.deleteById(example.getId());
            exampleGrid.setItems(exampleService.findAll());
        });

        exampleButtons.add(addExampleButton, editExampleButton, deleteExampleButton);
        exampleLayout.add(exampleHeader, exampleGrid, exampleButtons);

        // ===== BẢNG TỪ TRONG VÍ DỤ (EXAMPLE WORD) =====
        VerticalLayout exampleWordLayout = new VerticalLayout();
        exampleWordLayout.setSizeFull();
        H5 exampleWordHeader = new H5("Từ trong ví dụ");

        Grid<ExampleWord> exampleWordGrid = new Grid<>();
        exampleWordGrid.setHeight("300px");
        exampleWordGrid.addColumn(ew -> ew.getEntity().getId()).setHeader("Mã thực thể").setWidth("100px");
        exampleWordGrid.addColumn(ew -> ew.getEntity().getCharacterString()).setHeader("Ký tự").setWidth("80px");
        exampleWordGrid.addColumn(ew -> ew.getExampleWordId().getPosition()).setHeader("Vị trí").setWidth("80px");
        exampleWordGrid.setItems(new ArrayList<>());

        HorizontalLayout exampleWordButtons = new HorizontalLayout();
        Button addExampleWordButton = new Button("Thêm");
        Button editExampleWordButton = new Button("Sửa");
        Button deleteExampleWordButton = new Button("Xóa");

        // Thêm từ vào ví dụ
        addExampleWordButton.addClickListener(cl -> {
            Example example = exampleGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (example == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm từ vào ví dụ");
            dialog.setWidth("500px");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<EntityX> entityField = new ComboBox<>("Thực thể");
            entityField.setItems(entityService.findAll());
            entityField.setItemLabelGenerator(e -> e.getId() + " - " + e.getCharacterString() + " (" + e.getPronunciationString() + ")");
            entityField.setWidth("100%");

            IntegerField positionField = new IntegerField("Vị trí");
            positionField.setValue(1);
            positionField.setWidth("100%");

            dialogLayout.add(entityField, positionField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                if (entityField.getValue() != null && positionField.getValue() != null) {
                    ExampleWordId id = ExampleWordId.builder()
                            .exampleId(example.getId())
                            .entityId(entityField.getValue().getId())
                            .position(positionField.getValue().longValue())
                            .build();

                    ExampleWord exampleWord = ExampleWord.builder()
                            .exampleWordId(id)
                            .example(example)
                            .entity(entityField.getValue())
                            .build();

                    exampleWordService.save(exampleWord);
                    exampleWordGrid.setItems(exampleWordService.findByExampleIdOrderByPosition(example.getId()));
                    dialog.close();
                }
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Sửa từ trong ví dụ (chỉ sửa được position)
        editExampleWordButton.addClickListener(cl -> {
            Example example = exampleGrid.getSelectedItems().stream().findFirst().orElse(null);
            ExampleWord exampleWord = exampleWordGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (example == null || exampleWord == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa vị trí từ");
            dialog.setWidth("500px");

            VerticalLayout dialogLayout = new VerticalLayout();

            TextField entityField = new TextField("Thực thể");
            entityField.setValue(exampleWord.getEntity().getId() + " - " + exampleWord.getEntity().getCharacterString());
            entityField.setEnabled(false);
            entityField.setWidth("100%");

            IntegerField positionField = new IntegerField("Vị trí");
            positionField.setValue(exampleWord.getExampleWordId().getPosition().intValue());
            positionField.setWidth("100%");

            dialogLayout.add(entityField, positionField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                if (positionField.getValue() != null) {
                    // Xóa ExampleWord cũ
                    exampleWordService.deleteById(exampleWord.getExampleWordId());

                    // Tạo ExampleWord mới với position mới
                    ExampleWordId newId = ExampleWordId.builder()
                            .exampleId(example.getId())
                            .entityId(exampleWord.getEntity().getId())
                            .position(positionField.getValue().longValue())
                            .build();

                    ExampleWord newExampleWord = ExampleWord.builder()
                            .exampleWordId(newId)
                            .example(example)
                            .entity(exampleWord.getEntity())
                            .build();

                    exampleWordService.save(newExampleWord);
                    exampleWordGrid.setItems(exampleWordService.findByExampleIdOrderByPosition(example.getId()));
                    dialog.close();
                }
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Xóa từ khỏi ví dụ
        deleteExampleWordButton.addClickListener(cl -> {
            Example example = exampleGrid.getSelectedItems().stream().findFirst().orElse(null);
            ExampleWord exampleWord = exampleWordGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (example == null || exampleWord == null) {
                return;
            }

            exampleWordService.deleteById(exampleWord.getExampleWordId());
            exampleWordGrid.setItems(exampleWordService.findByExampleIdOrderByPosition(example.getId()));
        });

        exampleWordButtons.add(addExampleWordButton, editExampleWordButton, deleteExampleWordButton);
        exampleWordLayout.add(exampleWordHeader, exampleWordGrid, exampleWordButtons);

        // Khi chọn Example, hiển thị các ExampleWord của nó
        exampleGrid.addSelectionListener(listener -> {
            Example selectedExample = listener.getFirstSelectedItem().orElse(null);
            if (selectedExample != null) {
                List<ExampleWord> exampleWords = exampleWordService.findByExampleIdOrderByPosition(selectedExample.getId());
                exampleWordGrid.setItems(exampleWords);
            } else {
                exampleWordGrid.setItems(new ArrayList<>());
            }
        });

        // ===== BẢNG GHÉP THỰC THỂ VỚI VÍ DỤ (ENTITY EXAMPLE) =====
        VerticalLayout entityExampleLayout = new VerticalLayout();
        entityExampleLayout.setSizeFull();
        H5 entityExampleHeader = new H5("Thực thể - Ví dụ");

        // Bảng Entity
        VerticalLayout entitySubLayout = new VerticalLayout();
        H6 entityXHeader = new H6("Thực thể");

        Grid<EntityX> entityXGrid = new Grid<>();
        entityXGrid.setHeight("200px");
        entityXGrid.addColumn(EntityX::getId).setHeader("Mã").setWidth("75px").setFlexGrow(0);
        entityXGrid.addColumn(EntityX::getCharacterString).setHeader("Ký tự");
        entityXGrid.setItems(entityService.findAll());

        entitySubLayout.add(entityXHeader, entityXGrid);

        // Bảng EntityExample
        VerticalLayout entityExampleSubLayout = new VerticalLayout();
        H6 entityExampleListHeader = new H6("Ví dụ của thực thể");

        Grid<EntityExample> entityExampleGrid = new Grid<>();
        entityExampleGrid.setHeight("200px");
        entityExampleGrid.addColumn(ee -> ee.getExample().getId()).setHeader("Mã VD").setWidth("75px");
        entityExampleGrid.addColumn(ee -> ee.getExample().getSource() != null ? ee.getExample().getSource().getName() : "").setHeader("Nguồn");
        entityExampleGrid.setItems(new ArrayList<>());

        HorizontalLayout entityExampleButtons = new HorizontalLayout();
        Button addEntityExampleButton = new Button("Thêm");
        Button deleteEntityExampleButton = new Button("Xóa");

        // Thêm ví dụ cho thực thể
        addEntityExampleButton.addClickListener(cl -> {
            EntityX entity = entityXGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (entity == null) {
                return;
            }

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm ví dụ cho thực thể");
            dialog.setWidth("600px");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Example> exampleComboBox = new ComboBox<>("Chọn ví dụ");
            exampleComboBox.setItems(exampleService.findAll());
            exampleComboBox.setItemLabelGenerator(ex -> "Mã " + ex.getId() + " - " +
                (ex.getSource() != null ? ex.getSource().getName() : "Không có nguồn"));
            exampleComboBox.setWidth("100%");

            dialogLayout.add(exampleComboBox);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            saveButton.addClickListener(e -> {
                if (exampleComboBox.getValue() != null) {
                    EntityExampleId id = EntityExampleId.builder()
                            .entityId(entity.getId())
                            .exampleId(exampleComboBox.getValue().getId())
                            .build();

                    EntityExample entityExample = EntityExample.builder()
                            .id(id)
                            .entity(entity)
                            .example(exampleComboBox.getValue())
                            .build();

                    entityExampleService.save(entityExample);
                    entityExampleGrid.setItems(entityExampleService.findByEntityId(entity.getId()));
                    dialog.close();
                }
            });

            cancelButton.addClickListener(e -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        // Xóa ví dụ khỏi thực thể
        deleteEntityExampleButton.addClickListener(cl -> {
            EntityX entity = entityXGrid.getSelectedItems().stream().findFirst().orElse(null);
            EntityExample entityExample = entityExampleGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (entity == null || entityExample == null) {
                return;
            }

            entityExampleService.deleteById(entityExample.getId());
            entityExampleGrid.setItems(entityExampleService.findByEntityId(entity.getId()));
        });

        entityExampleButtons.add(addEntityExampleButton, deleteEntityExampleButton);
        entityExampleSubLayout.add(entityExampleListHeader, entityExampleGrid, entityExampleButtons);

        // Khi chọn Entity, hiển thị các Example của nó
        entityXGrid.addSelectionListener(selection -> {
            EntityX selectedEntity = selection.getFirstSelectedItem().orElse(null);
            if (selectedEntity != null) {
                entityExampleGrid.setItems(entityExampleService.findByEntityId(selectedEntity.getId()));
            } else {
                entityExampleGrid.setItems(new ArrayList<>());
            }
        });

        VerticalLayout entityExampleCombined = new VerticalLayout();
        entityExampleCombined.add(entitySubLayout, entityExampleSubLayout);
        entityExampleLayout.add(entityExampleHeader, entityExampleCombined);

        layout.add(exampleLayout, exampleWordLayout, entityExampleLayout);
        exampleLayout.setWidth("35%");
        exampleWordLayout.setWidth("30%");
        entityExampleLayout.setWidth("35%");

        return layout;
    }
}
