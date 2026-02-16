package com.liu.trachunom.view;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;

public class Header extends HorizontalLayout {
    public Header() {
        setWidthFull();
        getStyle()
            .set("background-color", "#f8f9fa")
            .set("padding", "10px 20px")
            .set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");
        Anchor a = new Anchor();
        a.setHref("");
        Image image = new Image();
        image.setSrc("https://www.gstatic.com/images/branding/searchlogo/ico/favicon.ico");
        image.setAlt("Logo");
        image.setWidth("32px");
        image.setHeight("32px");
        a.add(image);
        add(a);
        Input input = new Input();
        input.setPlaceholder("Tìm chữ Hán Nôm / quốc ngữ...");
        input.getStyle()
            .set("width", "300px")
            .set("padding", "8px 12px")
            .set("font-size", "16px")
            .set("border", "1px solid #ced4da")
            .set("border-radius", "4px");
        input.setValueChangeMode(ValueChangeMode.LAZY);
        input.addValueChangeListener((event) -> {
            String query = input.getValue();
            if (query != null && !query.trim().isEmpty()) {
                String encodedQuery = query.replace(" ", "%20");
                getUI().ifPresent(ui -> ui.navigate("?query=" + encodedQuery));
            }
        });
        add(input);
    }
}
