package com.liu.trachunom.controller.web.guest;

import com.liu.trachunom.service.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.service.CharacterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/thymeleaf")  // Changed from "/" to "/thymeleaf" to avoid conflict with Vaadin
public class GuestController {
    private final CharacterService characterService;
    private final EntityService entityService;

    @GetMapping("")
    public String indexGet(Model model) {
        return "guest/index";
    }

    @GetMapping("/search")
    public String searchGet(@RequestParam(required = false, defaultValue = "") String query,
                            Model model) {
        try {
            // Kiểm tra query không rỗng
            if (query == null || query.isEmpty()) {
                model.addAttribute("err", "Vui lòng nhập ký tự cần tìm");
                return "guest/error";
            }

            int codePoint = query.codePointAt(0);
            System.out.println("Unicode codepoint: " + codePoint);

            CharacterX character = characterService.findByUnicode(codePoint);
            if (character == null) {
                model.addAttribute("err", "Không tìm thấy kí tự " + new String(Character.toChars(codePoint)));
                return "guest/error";
            }
            model.addAttribute("character", character);
            model.addAttribute("entities", entityService.findByCharacter(character));
        } catch (Exception e) {
            model.addAttribute("err", e.getMessage());
            e.printStackTrace();
            return "guest/error";
        }
        return "guest/search";
    }
}

