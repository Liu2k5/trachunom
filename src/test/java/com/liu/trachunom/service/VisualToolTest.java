package com.liu.trachunom.service;

import com.liu.trachunom.entity.SubStructure;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VisualToolTest {
    @Autowired
    private VisualTool visualTool;

    @Autowired
    private StructureService structureService;

    @Test
    public void test() {
        List<SubStructure> structure = structureService.findById(29L).getSubStructures();
        System.out.println(structure.size());
        HorizontalLayout layout = visualTool.drawStructure(structure);
        layout.getChildren().forEach(component -> {
            System.out.println("Component: " + component.getClass().getSimpleName());
            System.out.println("ID: " + component.getId().orElse("No ID"));
        });
    }
}
