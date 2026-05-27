package com.liu.trachunom.service;

import com.liu.trachunom.service.structure.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class StructureServiceTest {
    @Autowired
    private StructureService structureService;

//    @Test
//    public void test1() {
//        List<Structure> foundStructures = structureService.getAllVariantStructuresByStructureId(949L);
////        assert foundStructures.size() == 2;
//        System.out.println(foundStructures.size());
//    }
}
