package com.liu.trachunom.service;


import com.liu.trachunom.entity.EntityComposition;
import com.liu.trachunom.entity.EntityEvolution;
import com.liu.trachunom.entity.EntityX;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static com.helger.commons.mock.CommonsAssert.assertEquals;

@SpringBootTest
public class EntityServiceTest {
    @Autowired
    private EntityService entityService;
    @Autowired
    private EntityCompositionService entityCompositionService;

    @Test
    public void test() {
        List<EntityX> results = entityService.findByQuery("失情");
        System.out.println(results.size());
        List<EntityComposition> compositions = entityCompositionService.findByParentEntityId(results.get(0).getId());
        System.out.println(compositions.size());
    }

    @Test
    public void test2() {
        EntityX example = entityService.findById(10L);
        List<EntityX> results = entityService.findSynonyms(example);
        assertEquals(1, results.size());
    }
}
