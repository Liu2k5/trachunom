package com.liu.trachunom.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class IndexView extends VerticalLayout {

    public IndexView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background", "linear-gradient(135deg, #667eea 0%, #764ba2 100%)");

        // Main content container
        VerticalLayout contentBox = new VerticalLayout();
//        contentBox.setWidth("600px");
//        contentBox.setPadding(true);
        contentBox.getStyle()
            .set("background", "white")
            .set("border-radius", "16px")
            .set("box-shadow", "0 20px 60px rgba(0,0,0,0.3)");

        // Title
        H1 title = new H1("Tra Cứu Chữ Nôm");
        title.getStyle()
            .set("color", "#667eea")
            .set("text-align", "center")
            .set("margin-bottom", "10px");

        // Description
        Paragraph description = new Paragraph(
            "Xin chào đến với trang web Tra Chữ Nôm. Đây là nơi bạn có thể tra cứu các ký tự chữ Nôm một cách dễ dàng và nhanh chóng."
        );
        description.getStyle()
            .set("text-align", "center")
            .set("color", "#666")
            .set("margin-bottom", "30px");

        // Admin link
        RouterLink adminLink = new RouterLink("Quản lý từ điển", DictionaryManagementView.class);
        adminLink.getStyle()
            .set("margin-top", "20px")
            .set("text-align", "center")
            .set("color", "#667eea")
            .set("text-decoration", "none")
            .set("font-weight", "500");

        contentBox.add(title, description, adminLink);
        contentBox.setAlignItems(Alignment.CENTER);

        add(contentBox);
    }
}

