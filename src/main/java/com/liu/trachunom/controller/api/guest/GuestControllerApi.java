package com.liu.trachunom.controller.api.guest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class GuestControllerApi {
    // @GetMapping("unicodeToCharacter")
    // public String unicodeToCharacterGet(@RequestParam("unicode") int unicode) {
    //     String character = new String(Character.toChars(unicode));
    //     return character;
    // }

}
