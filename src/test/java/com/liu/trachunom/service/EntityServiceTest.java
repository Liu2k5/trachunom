package com.liu.trachunom.service;


import com.liu.trachunom.entity.entity.EntityX;
import com.liu.trachunom.service.entity.EntityCompositionService;
import com.liu.trachunom.service.entity.EntityService;
import com.liu.trachunom.service.structure.StructureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.helger.commons.mock.CommonsAssert.assertEquals;

@SpringBootTest
public class EntityServiceTest {
    @Autowired
    private EntityService entityService;

//    @Test
//    public void test() {
//        List<EntityX> results = entityService.findByQuery("失情");
//        System.out.println(results.size());
//        List<EntityComposition> compositions = entityCompositionService.findByParentEntityId(results.get(0).getId());
//        System.out.println(compositions.size());
//    }
//
//    @Test
//    public void test2() {
//        EntityX example = entityService.findById(10L);
//        List<EntityX> results = entityService.findSynonyms(example);
//        assertEquals(1, results.size());
//    }

    @Test
    public void test3() {
        EntityX example = entityService.findById(29L);
        assertEquals("[阿貶]", entityService.getHnomStringById(example.getId()));
    }

//    @Test
//    public void test4() {
//        EntityX entity = entityService.findById(407L);
//        List<EntityX> synoyms = entityService.findSynonyms(entity);
//        when(meaningService.findAll()).then(return )
//        System.out.println(synoyms.size());
//    }

    @Test
    public void test4() {
        String charString = entityService.getHnomStringById(709L);
//        assertEquals(charString.codePointAt(0), 0x2ed7);
        System.out.println(charString);
    }
}
