package com.liu.trachunom.view;

import com.liu.trachunom.entity.*;
import com.liu.trachunom.service.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
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
    @Autowired private MeaningService meaningService;
    @Autowired private EntityService entityService;
    @Autowired private QuocNguService quocNguService;
    @Autowired private ExplanationService explanationService;
    @Autowired private StyleService styleService;
    @Autowired private SourceService sourceService;
    @Autowired private TradSimpStandardService tradSimpStandardService;
    @Autowired private SubStructureService subStructureService;
    @Autowired private PronunciationService pronunciationService;
    @Autowired private PronunciationChangeService pronunciationChangeService;
    @Autowired private EtymologyService etymologyService;
    @Autowired private VisualTool visualTool;
    @Autowired private PronunciationClassificationService pronunciationClassificationService;

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
        Binder<Radical> binder1 = new Binder<>();

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull(); // Thay vì setWidth/setHeight riêng lẻ

        VerticalLayout characterLayout = new VerticalLayout();
        characterLayout.setSizeFull();

        H5 radicalHeader = new H5("Bộ thủ và kí tự");
        Grid<Radical> radicalGrid = new Grid<>();
        radicalGrid.setHeight("300px");
        radicalGrid.addColumn(Radical::getString).setWidth("50px").setFlexGrow(0);
        radicalGrid.addColumn(Radical::getId, "id").setHeader("Mã bộ thủ");
        radicalGrid.addColumn(Radical::getRadicalUnicode, "radicalUnicode").setHeader("Unicode bộ thủ");
        radicalGrid.addColumn(Radical::getUnicode, "meaning").setHeader("Unicode kí tự");
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

            binder1.setBean(radical);
            String id = radical.getId();

            Dialog dialog = new Dialog();

            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField idField = new TextField("Mã bộ thủ");
            idField.setValue(radical.getId());
            binder1.forField(idField)
                    .asRequired("Vui lòng nhập mã bộ thủ")
                    .withValidator(string -> !radicalService.existsById(string) || string.equals(id), "Đã tồn tại mã bộ thủ này")
                    .bind(Radical::getId, Radical::setId);

            IntegerField radicalUnicodeField = new IntegerField("Unicode bộ thủ");
            radicalUnicodeField.setValue(radical.getRadicalUnicode());
            binder1.forField(radicalUnicodeField)
                    .withValidator(Character::isDefined, "Unicode không hợp lệ")
                    .bind(Radical::getRadicalUnicode, Radical::setRadicalUnicode);

            IntegerField unicodeField = new IntegerField("Unicode kí tự");
            unicodeField.setValue(radical.getUnicode());
            binder1.forField(unicodeField)
                    .withValidator(integer -> (integer != null && Character.isDefined(integer)), "Unicode không hợp lệ")
                    .bind(Radical::getUnicode, Radical::setUnicode);

            dialogLayout.add(idField, radicalUnicodeField, unicodeField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);

            binder1.validate();

            saveButton.addClickListener(ev -> {
                if (binder1.validate().isOk()) {
                    Radical editedRadical = binder1.getBean();
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

        Binder<CharacterX> binder2 = new Binder<>();
        binder2.setBean(new CharacterX());

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
            binder2.setBean(character);
            Integer unicode = character.getUnicode();

            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            IntegerField unicodeField = new IntegerField("Unicode");
            unicodeField.setValue(character.getUnicode());
            binder2.forField(unicodeField)
                    .withValidator(integer -> integer != null && integer > 0, "Unicode phải là số nguyên dương")
                    .withValidator(Character::isDefined, "Unicode không hợp lệ")
                    .withValidator(integer -> !characterService.existsByUnicode(integer) || integer.equals(unicode), "Đã tồn tại kí tự với Unicode này")
                    .bind(CharacterX::getUnicode, CharacterX::setUnicode);

            ComboBox<Radical> radicalField = new ComboBox<>("Bộ thủ");
            radicalField.setItems(radicalService.findAll());
            radicalField.setItemLabelGenerator((Radical radical) -> radical.getId() + " - " + radical.getString());
            radicalField.setValue(character.getRadical());
            binder2.forField(radicalField).asRequired("Vui lòng chọn bộ thủ")
                    .bind(CharacterX::getRadical, CharacterX::setRadical);

            IntegerField additionalStrokeNumberField = new IntegerField("Số nét phụ");
            additionalStrokeNumberField.setValue(character.getAdditionalStrokeNumber());
            binder2.forField(additionalStrokeNumberField).withValidator(Objects::nonNull, "Số nét phụ phải là số nguyên")
                    .bind(CharacterX::getAdditionalStrokeNumber, CharacterX::setAdditionalStrokeNumber);

            IntegerField totalStrokeNumberField = new IntegerField("Tổng số nét");
            totalStrokeNumberField.setValue(character.getTotalStrokeNumber());
            binder2.forField(totalStrokeNumberField).withValidator(integer -> integer != null && integer > 0, "Tổng số nét phải là số nguyên dương")
                    .bind(CharacterX::getTotalStrokeNumber, CharacterX::setTotalStrokeNumber);

            dialogLayout.add(unicodeField, radicalField, additionalStrokeNumberField, totalStrokeNumberField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder2.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder2.validate().isOk()) {
                    CharacterX editedCharacter = binder2.getBean();
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
        Binder<QuocNgu> binder3 = new Binder<>();

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
                System.out.println("null???");
                return;
            }
            binder3.setBean(quocNgu);
            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField descriptionField = new TextField("Mô tả âm đọc");
            descriptionField.setValue(quocNgu.getDescription());
            binder3.forField(descriptionField)
                    .asRequired("Vui lòng nhập mô tả âm đọc")
                    .withValidator(string -> !quocNguService.existsByDescription(string.toLowerCase().trim()), "Đã tồn tại âm đọc này")
                    .bind(QuocNgu::getDescription, QuocNgu::setDescription);
            dialogLayout.add(descriptionField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder3.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder3.validate().isOk()) {
                    QuocNgu editedQuocNgu = binder3.getBean();
                    editedQuocNgu.setDescription(descriptionField.getValue().toLowerCase().trim());
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

        Binder<Explanation> binder4 = new Binder<>();

        Grid<Explanation> explanationGrid = new Grid<>();
        explanationGrid.setHeight("300px");
        explanationGrid.addColumn(Explanation::getId, "id").setHeader("Mã").setWidth("75px").setFlexGrow(0);
        explanationGrid.addColumn(Explanation::getDescription, "description").setHeader("Mô tả giải nghĩa");
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
            binder4.setBean(explanation);
            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField descriptionField = new TextField("Mô tả giải nghĩa");
            descriptionField.setValue(explanation.getDescription());
            binder4.forField(descriptionField)
                    .asRequired("Vui lòng nhập mô tả giải nghĩa")
                    .withValidator(string -> !explanationService.existsByDescription(string.toLowerCase().trim()), "Đã tồn tại giải nghĩa này")
                    .bind(Explanation::getDescription, Explanation::setDescription);
            dialogLayout.add(descriptionField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder4.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder4.validate().isOk()) {
                    Explanation editedExplanation = binder4.getBean();
                    editedExplanation.setDescription(descriptionField.getValue().toLowerCase().trim());
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

        // Binder cho Language
        Binder<Language> binder5 = new Binder<>();

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
            binder5.setBean(language);
            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField abbreviationField = new TextField("Tên viết tắt");
            abbreviationField.setValue(language.getAbbreviation());
            binder5.forField(abbreviationField)
                    .asRequired("Vui lòng nhập tên viết tắt")
                    .withValidator(string -> !languageService.existsByAbbreviation(string.trim()) || string.trim().equals(language.getAbbreviation()), "Đã tồn tại ngôn ngữ này")
                    .bind(Language::getAbbreviation, Language::setAbbreviation);
            dialogLayout.add(abbreviationField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder5.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder5.validate().isOk()) {
                    Language editedLanguage = binder5.getBean();
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

        // Binder cho Style
        Binder<Style> binder6 = new Binder<>();

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
            binder6.setBean(style);
            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField descriptionField = new TextField("Mô tả phong cách");
            descriptionField.setValue(style.getDescription());
            binder6.forField(descriptionField)
                    .asRequired("Vui lòng nhập mô tả phong cách")
                    .withValidator(string -> !styleService.existsByDescription(string.toLowerCase().trim()) || string.toLowerCase().trim().equals(style.getDescription()), "Đã tồn tại phong cách này")
                    .bind(Style::getDescription, Style::setDescription);
            dialogLayout.add(descriptionField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder6.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder6.validate().isOk()) {
                    Style editedStyle = binder6.getBean();
                    editedStyle.setDescription(descriptionField.getValue().toLowerCase().trim());
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

        // Binder cho Source
        Binder<Source> binder7 = new Binder<>();

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
            binder7.setBean(source);
            Dialog dialog = new Dialog();
            HorizontalLayout dialogLayout = new HorizontalLayout();
            TextField nameField = new TextField("Tên nguồn");
            nameField.setWidth("400px");
            nameField.setValue(source.getName());
            binder7.forField(nameField)
                    .asRequired("Vui lòng nhập tên nguồn")
                    .withValidator(string -> !sourceService.existsByName(string.trim()) || string.trim().equals(source.getName()), "Đã tồn tại nguồn này")
                    .bind(Source::getName, Source::setName);
            dialogLayout.add(nameField);
            dialog.add(dialogLayout);
            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder7.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder7.validate().isOk()) {
                    Source editedSource = binder7.getBean();
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
        H5 subStructureHeader = new H5("Cấu tạo con");

        // Grid hiển thị SubStructure (khai báo trước để có thể dùng trong events)
        Grid<SubStructure> subStructureGrid = new Grid<>();
        subStructureGrid.addColumn(sub -> sub.getSubStructure().getCharacter().getString(), "character").setWidth("50px").setFlexGrow(0);
        subStructureGrid.addColumn(sub -> sub.getStructureClassification().getDescription(), "classification").setHeader("Phân loại");
        subStructureGrid.addColumn(SubStructure::getQuantity).setHeader("Số lượng").setWidth("100px").setFlexGrow(0);

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
                                .subStructures(new ArrayList<>())
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
            subStructureGrid.setItems(new ArrayList<>());
        });

        structureButtons.add(addStructureButton, editStructureButton, deleteStructureButton);

        // Nút thêm, sửa, xóa SubStructure
        HorizontalLayout subStructureButtons = new HorizontalLayout();
        Button addSubStructureButton = new Button("Thêm");
        Button editSubStructureButton = new Button("Sửa");
        Button deleteSubStructureButton = new Button("Xóa");

        addSubStructureButton.addClickListener(cl -> {
            Structure structure = structureGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (structure == null) {
                return;
            }

            Binder<SubStructure> binder = new Binder();

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Thêm cấu tạo con");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Structure> subStructureField = new ComboBox<>("Cấu tạo con");
            subStructureField.setItems(structureService.findAll());
            subStructureField.setItemLabelGenerator(s -> s.getId() + " - " + s.getCharacter().getString());
            subStructureField.setWidth("300px");
            binder.forField(subStructureField).asRequired("Vui lòng chọn cấu tạo con").bind(SubStructure::getSubStructure, SubStructure::setSubStructure);

            ComboBox<StructureClassification> classificationField = new ComboBox<>("Phân loại");
            classificationField.setItems(structureClassificationService.findAll());
            classificationField.setItemLabelGenerator(StructureClassification::getDescription);
            classificationField.setWidth("300px");
            binder.forField(classificationField).asRequired("Vui lòng chọn phân loại").bind(SubStructure::getStructureClassification, SubStructure::setStructureClassification);

            IntegerField quantityField = new IntegerField("Số lượng");
            quantityField.setValue(1);
            quantityField.setWidth("300px");
            binder.forField(quantityField).asRequired("Vui lòng nhập số lượng")
                    .withValidator(integer -> integer != null && integer > 0, "Số lượng phải là số nguyên dương")
                    .bind(SubStructure::getQuantity, SubStructure::setQuantity);

            dialogLayout.add(subStructureField, classificationField, quantityField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    if (subStructureField.getValue() != null && classificationField.getValue() != null) {
                        SubStructureId subStructureId = SubStructureId.builder()
                                .structureId(structure.getId())
                                .subStructureId(subStructureField.getValue().getId())
                                .classificationId(classificationField.getValue().getId())
                                .build();

                        SubStructure newSubStructure = SubStructure.builder()
                                .id(subStructureId)
                                .structure(structure)
                                .subStructure(subStructureField.getValue())
                                .structureClassification(classificationField.getValue())
                                .quantity(quantityField.getValue())
                                .build();

                        subStructureService.save(newSubStructure);

                        // Refresh structure để lấy subStructures mới
                        Structure refreshedStructure = structureService.findById(structure.getId());
                        if (refreshedStructure != null) {
                            subStructureGrid.setItems(refreshedStructure.getSubStructures());
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

            subStructureField.addValueChangeListener(vcl -> {
                Structure selectedSubStructure = vcl.getValue();
                preview.removeAll();
                preview.add(previewHeader);
                if (selectedSubStructure != null) {
                    HorizontalLayout structurePreview = structureService.drawStructure(selectedSubStructure.getSubStructures());
                    structurePreview.setSpacing(true);
                    structurePreview.setWidthFull();
                    preview.add(structurePreview);
                }
            });

            dialog.open();
        });

        editSubStructureButton.addClickListener(cl -> {
            Structure structure = structureGrid.getSelectedItems().stream().findFirst().orElse(null);
            SubStructure subStructure = subStructureGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (structure == null || subStructure == null) {
                return;
            }

            Binder<SubStructure> binder = new Binder<>();

            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Sửa cấu tạo con");

            VerticalLayout dialogLayout = new VerticalLayout();

            ComboBox<Structure> subStructureField = new ComboBox<>("Cấu tạo con");
            subStructureField.setItems(structureService.findAll());
            subStructureField.setItemLabelGenerator(s -> s.getId() + " - " + s.getCharacter().getString());
            subStructureField.setValue(subStructure.getSubStructure());
            subStructureField.setEnabled(false); // Không cho sửa khóa chính
            subStructureField.setWidth("300px");

            ComboBox<StructureClassification> classificationField = new ComboBox<>("Phân loại");
            classificationField.setItems(structureClassificationService.findAll());
            classificationField.setItemLabelGenerator(StructureClassification::getDescription);
            classificationField.setValue(subStructure.getStructureClassification());
            classificationField.setEnabled(false); // Không cho sửa khóa chính
            classificationField.setWidth("300px");

            IntegerField quantityField = new IntegerField("Số lượng");
            quantityField.setValue(subStructure.getQuantity());
            quantityField.setWidth("300px");
            binder.forField(quantityField).asRequired("Vui lòng nhập số lượng")
                    .withValidator(integer -> integer != null && integer > 0, "Số lượng phải là số nguyên dương")
                    .bind(SubStructure::getQuantity, SubStructure::setQuantity);

            dialogLayout.add(subStructureField, classificationField, quantityField);
            dialog.add(dialogLayout);

            HorizontalLayout dialogButtons = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl2 -> {
                if (binder.validate().isOk()) {
                    subStructure.setQuantity(quantityField.getValue());
                    subStructureService.save(subStructure);

                    // Refresh structure để lấy subStructures mới
                    Structure refreshedStructure = structureService.findById(structure.getId());
                    if (refreshedStructure != null) {
                        subStructureGrid.setItems(refreshedStructure.getSubStructures());
                    }
                    dialog.close();
                }
            });

            cancelButton.addClickListener(cl2 -> dialog.close());
            dialogButtons.add(saveButton, cancelButton);
            dialog.add(dialogButtons);
            dialog.open();
        });

        deleteSubStructureButton.addClickListener(cl -> {
            Structure structure = structureGrid.getSelectedItems().stream().findFirst().orElse(null);
            SubStructure subStructure = subStructureGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (structure == null || subStructure == null) {
                return;
            }

            subStructureService.deleteById(subStructure.getId());
            structure.getSubStructures().remove(subStructure);
            structureService.save(structure);

            // Refresh structure để lấy subStructures mới
            Structure refreshedStructure = structureService.findById(structure.getId());
            if (refreshedStructure != null) {
                subStructureGrid.setItems(refreshedStructure.getSubStructures());
            }
            structureGrid.setItems(structureService.findAll());
        });

        subStructureButtons.add(addSubStructureButton, editSubStructureButton, deleteSubStructureButton);

        HorizontalLayout structureAndSubStructureLayout = new HorizontalLayout();
        VerticalLayout structureSubLayout = new VerticalLayout();
        structureSubLayout.setSpacing(true);
        structureSubLayout.add(structureHeader, structureGrid, structureButtons);
        VerticalLayout subStructureLayout = new VerticalLayout();
        subStructureLayout.setSpacing(true);
        subStructureLayout.add(subStructureHeader, subStructureGrid, subStructureButtons);
        structureAndSubStructureLayout.add(structureSubLayout, subStructureLayout);
        structureSubLayout.setWidth("40%");
        subStructureLayout.setWidth("60%");

        HorizontalLayout structurePreviewLayout = new HorizontalLayout();
        H5 structurePreviewHeader = new H5("Xem trước cấu tạo");
        structurePreviewLayout.add(structurePreviewHeader);
        structureLayout.add(structureAndSubStructureLayout, structurePreviewLayout);

        // Khi chọn Structure, hiển thị các SubStructure của nó
        structureGrid.addSelectionListener(selection -> {
            Structure selectedStructure = selection.getFirstSelectedItem().orElse(null);

            structureLayout.removeAll();

            HorizontalLayout newStructurePreview = new HorizontalLayout();
            if (selectedStructure != null) {
                subStructureGrid.setItems(selectedStructure.getSubStructures());
                newStructurePreview = structureService.drawStructure(selectedStructure.getSubStructures());
            } else {
                subStructureGrid.setItems(new ArrayList<>());
                newStructurePreview = new HorizontalLayout();
                newStructurePreview.add(structurePreviewHeader);
            }
            newStructurePreview.setSpacing(true);
            newStructurePreview.setWidthFull();
            structureLayout.add(structureAndSubStructureLayout, newStructurePreview);
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
        Grid<PronunciationChange> pronunciationChangeGrid = new Grid<>();
        pronunciationChangeGrid.setHeight("400px");
        pronunciationChangeGrid.addColumn((PronunciationChange pronunciationChange) ->
                (pronunciationChange.getId().getPreviousPronunciationId() + " - " +
                pronunciationChange.getPreviousPronunciation().getQuocNgu().getDescription()), "previousPronunciation").setHeader("Âm trước");
        pronunciationChangeGrid.addColumn((PronunciationChange pronunciationChange) ->
                pronunciationChange.getPronunciationClassification().getDescription(), "pronunciationClassifcation").setHeader("Phân loại");
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

            Binder<PronunciationChange> binder = new Binder<>();
            binder.setBean(new PronunciationChange());

            HorizontalLayout pronunciationChangeForm = new HorizontalLayout();

            ComboBox<Pronunciation> previousPronunciationField = new ComboBox("Âm trước");
            previousPronunciationField.setItems(pronunciationService.findAll());
            previousPronunciationField.setItemLabelGenerator((Pronunciation p) -> p.getQuocNgu().getDescription());
            previousPronunciationField.setValue(binder.getBean().getPreviousPronunciation());
            previousPronunciationField.setValue(binder.getBean().getPreviousPronunciation());
            binder.forField(previousPronunciationField).asRequired("Vui lòng chọn âm trước")
                    .withValidator((p) -> pronunciationChangeService.findById(new PronunciationChangeId().builder()
                            .pronunciationId(pronunciation.getId())
                            .previousPronunciationId(previousPronunciationField.getValue() != null ? previousPronunciationField.getValue().getId() : null)
                            .pronunciationClassificationId(binder.getBean().getPronunciationClassification() != null ? binder.getBean().getPronunciationClassification().getId() : null)
                            .build()) == null, "Biến đổi âm đọc này đã tồn tại")
                    .bind(PronunciationChange::getPreviousPronunciation, PronunciationChange::setPreviousPronunciation);

            ComboBox<PronunciationClassification> classificationField = new ComboBox<>("Phân loại");
            classificationField.setItems(pronunciationClassificationService.findAll());
            classificationField.setItemLabelGenerator(PronunciationClassification::getDescription);
            classificationField.setValue(binder.getBean().getPronunciationClassification());
            binder.forField(classificationField).asRequired("Vui lòng chọn phân loại")
                    .withValidator((p) -> pronunciationChangeService.findById(new PronunciationChangeId().builder()
                            .pronunciationId(pronunciation.getId())
                            .previousPronunciationId(binder.getBean().getPreviousPronunciation() != null ? binder.getBean().getPreviousPronunciation().getId() : null)
                            .pronunciationClassificationId(classificationField.getValue() != null ? classificationField.getValue().getId() : null)
                            .build()) == null, "Biến đổi âm đọc này đã tồn tại")
                    .bind(PronunciationChange::getPronunciationClassification, PronunciationChange::setPronunciationClassification);

            pronunciationChangeForm.add(previousPronunciationField, classificationField);

            HorizontalLayout pronunciationChangeFormButton = new HorizontalLayout();
            Button saveButton = new Button("Lưu");
            Button cancelButton = new Button("Hủy");

            binder.validate();

            saveButton.addClickListener(cl3 -> {
                if (binder.validate().isOk()) {
                    PronunciationChange editedPronunciationChange  = PronunciationChange.builder()
                            .id(new PronunciationChangeId().builder()
                                    .pronunciationId(pronunciation.getId())
                                    .previousPronunciationId(previousPronunciationField.getValue().getId())
                                    .pronunciationClassificationId(classificationField.getValue().getId())
                                    .build())
                            .pronunciation(pronunciation)
                            .previousPronunciation(previousPronunciationField.getValue())
                            .pronunciationClassification(classificationField.getValue())
                            .build();

                pronunciationChangeService.save(editedPronunciationChange);
                pronunciation.getPronunciationChanges().add(editedPronunciationChange);
                pronunciationService.save(pronunciation);
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

            previousPronunciationField.addValueChangeListener(vcl -> {
                pronunciationChangePreview.removeAll();
                pronunciationChangePreview.add(pronunciationChangePreviewHeader);
                Pronunciation selectedPreviousPronunciation = vcl.getValue();
                if (selectedPreviousPronunciation != null) {
                    pronunciationChangePreview.add(pronunciationService.drawPronunciation(selectedPreviousPronunciation));
                }
            });

            dialog.add(pronunciationChangeForm, pronunciationChangeFormButton, pronunciationChangePreview);
            dialog.open();
        });

        deletePronunciationChangeButton.addClickListener(cl2 -> {
            PronunciationChange pronunciationChange = pronunciationChangeGrid.getSelectedItems().stream().findFirst().orElse(null);
            if (pronunciationChange == null) {
                return;
            }
            Pronunciation pronunciation = pronunciationChange.getPronunciation();
            pronunciation.getPronunciationChanges().remove(pronunciationChange);
            pronunciationChangeService.delete(pronunciationChange.getId());
            pronunciationService.save(pronunciation);
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
            pronunciationChangeGrid.setItems(selectedPronunciation.getPronunciationChanges());
            pronunciationChangePreview.add(pronunciationService.drawPronunciation(selectedPronunciation.getPronunciationChanges()));
        });


        VerticalLayout meaningLayout = new VerticalLayout();
        meaningLayout.setSizeFull();
        H5 meaningHeader = new H5("Nghĩa");

        Grid<Meaning> meaningGrid = new Grid<>();
        meaningGrid.setHeight("400px");
        meaningGrid.addColumn(Meaning::getId).setHeader("Mã").setWidth("75px").setFlexGrow(0);
        meaningGrid.addColumn(meaning -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (Explanation explanation : meaning.getExplanations()) {
                stringBuilder.append(explanation.getDescription()).append("\n");
            }
            if (stringBuilder.length() >= 2) {
                stringBuilder.setLength(stringBuilder.length() - 2); // Xóa dấu chấm
            }
            return stringBuilder.toString();
        }).setHeader("Giải nghĩa");
        meaningGrid.setItems(meaningService.findAll());

        HorizontalLayout meaningButtons = new HorizontalLayout();
        Button addMeaningButton = new Button("Thêm");
        Button editMeaningButton = new Button("Sửa");
        Button deleteMeaningButton = new Button("Xóa");
        meaningButtons.add(addMeaningButton, editMeaningButton, deleteMeaningButton);

        meaningLayout.add(meaningHeader, meaningGrid, meaningButtons);

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
        entityGrid.setHeight("600px");
        entityGrid.addColumn(EntityX::getId).setHeader("Mã").setWidth("75px").setFlexGrow(0);
        entityGrid.addColumn(entity -> entity.getStructure().getCharacter().getString()).setHeader("Ký tự").setWidth("60px");
        entityGrid.setItems(entityService.findAll());

        HorizontalLayout entityButtons = new HorizontalLayout();
        Button addEntityButton = new Button("Thêm");
        Button editEntityButton = new Button("Sửa");
        Button deleteEntityButton = new Button("Xóa");
        entityButtons.add(addEntityButton, editEntityButton, deleteEntityButton);

        leftLayout.add(entityHeader, entityGrid, entityButtons);

        VerticalLayout rightLayout = new VerticalLayout();
        rightLayout.setSizeFull();

        layout.add(leftLayout, rightLayout);
        leftLayout.setWidth("50%");
        rightLayout.setWidth("50%");

        return layout;
    }

    private HorizontalLayout complementaryLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();

        VerticalLayout leftLayout = new VerticalLayout();
        leftLayout.setSizeFull();
        H5 etymologyHeader = new H5("Từ nguyên");

        Grid<Etymology> etymologyGrid = new Grid<>();
        etymologyGrid.setHeight("600px");
        etymologyGrid.addColumn(Etymology::getId).setHeader("Mã").setWidth("75px").setFlexGrow(0);
//        etymologyGrid.addColumn(Etymology::getDescription).setHeader("Mô tả");
        etymologyGrid.setItems(etymologyService.findAll());

        HorizontalLayout etymologyButtons = new HorizontalLayout();
        Button addEtymologyButton = new Button("Thêm");
        Button editEtymologyButton = new Button("Sửa");
        Button deleteEtymologyButton = new Button("Xóa");
        etymologyButtons.add(addEtymologyButton, editEtymologyButton, deleteEtymologyButton);

        leftLayout.add(etymologyHeader, etymologyGrid, etymologyButtons);

        VerticalLayout rightLayout = new VerticalLayout();
        rightLayout.setSizeFull();

        layout.add(leftLayout, rightLayout);
        leftLayout.setWidth("50%");
        rightLayout.setWidth("50%");

        return layout;
    }
}

