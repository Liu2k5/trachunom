package com.liu.trachunom.controller.web.admin;

import java.util.List;

import com.liu.trachunom.dto.*;
import com.liu.trachunom.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liu.trachunom.entity.CharacterX;
import com.liu.trachunom.entity.Structure;
import com.liu.trachunom.entity.SubStructure;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/dictionary_management")
public class AdminController {
    private final RadicalService radicalService;
    private final ClassificationService classificationService;
    private final LanguageService languageService;
    private final CharacterService characterService;
    private final StructureService structureService;
    private final SubStructureService subStructureService;
    private final MeaningService meaningService;
    private final EntityService entityService;
    private final StandardisedService standardisedEService;

    @GetMapping("")
    public String dictionaryManagementGet(  Model model,
                                            HttpSession session
    ) {
        if (session.getAttribute("radicals") == null) {
            session.setAttribute("radicals", radicalService.findAll());
        }
        if (session.getAttribute("classifications") == null) {
            session.setAttribute("classifications", classificationService.findAll());
        }
        if (session.getAttribute("languages") == null) {
            session.setAttribute("languages", languageService.findAll());
        }
        if (session.getAttribute("radicalsString") == null) {
            session.setAttribute("radicalsString", radicalService.findAllRadicalStrings());
        }
        
        // Khởi tạo các DTO cho form
        model.addAttribute("structureFormDto", new StructureFormDto());
        
        return "admin/dictionary_management";
    }

    @PostMapping("character")
    public String characterPost(    @ModelAttribute("characterDto") CharacterDto characterDto,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("characterDto", characterDto);
        try {
            characterService.save(characterDto);
        } catch (Exception e) {
            model.addAttribute("err", e.getMessage());
            return "admin/dictionary_management";
        }
        redirectAttributes.addFlashAttribute("msg", "Lưu kí tự thành công");
        return "redirect:/admin/dictionary_management";
    }

    @PostMapping("structure")
    public String structurePost(    @ModelAttribute("structureFormDto") StructureFormDto structureFormDto,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("structureFormDto", structureFormDto);
        try {
            structureService.save(structureFormDto);
        } catch (Exception e) {
            model.addAttribute("err", e.getMessage());
            return "admin/dictionary_management";
        }
        redirectAttributes.addFlashAttribute("msg", "Lưu cấu tạo thành công");
        return "redirect:/admin/dictionary_management";
    }

    @PostMapping("meaning")
    public String meaningPost(@ModelAttribute("meaningDto") MeaningDto meaningDto,
                                Model model,
                                HttpSession session,
                                RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("meaningDto", meaningDto);
        try {
            meaningService.save(meaningDto);
        } catch (Exception e) {
            model.addAttribute("err", e.getMessage());
            return "admin/dictionary_management";
        }
        redirectAttributes.addFlashAttribute("msg", "Lưu nghĩa thành công");
        return "redirect:/admin/dictionary_management";
    }

    @PostMapping("entity")
    public String entityPost(    @ModelAttribute("entityDto") EntityDto entityDto,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("entityDto", entityDto);
        try {
            entityService.save(entityDto);
        } catch (Exception e) {
            model.addAttribute("err", e.getMessage());
            return "admin/dictionary_management";
        }
        redirectAttributes.addFlashAttribute("msg", "Lưu thực thể thành công");
        return "redirect:/admin/dictionary_management";
    }

    @PostMapping("standardisedEntity")
    public String standardisedEntityPost(    @ModelAttribute("standardisedDto") StandardisedDto standardisedDto,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("standardisedDto", standardisedDto);
        try {
            standardisedEService.save(standardisedDto);
        } catch (Exception e) {
            model.addAttribute("err", e.getMessage());
            return "admin/dictionary_management";
        }
        redirectAttributes.addFlashAttribute("msg", "Lưu thực thể chuẩn hóa thành công");
        return "redirect:/admin/dictionary_management";
    }
}
