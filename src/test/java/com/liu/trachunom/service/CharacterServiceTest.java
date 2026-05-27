package com.liu.trachunom.service;


import com.liu.trachunom.service.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CharacterServiceTest {
    
    @Autowired
    private CharacterService characterService;

//    @Test
//    public void testFindByUnicode() {
//        Integer unicode = 152741;
//        CharacterX character = characterService.findByUnicode(unicode);
//
//        // Test sẽ pass nếu tìm thấy character hoặc null
//        if (character != null) {
//            assertEquals(unicode, character.getUnicode());
//        }
//    }
}
