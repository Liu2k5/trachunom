package com.liu.trachunom.controller.api.admin;

import com.liu.trachunom.dto.*;
import com.liu.trachunom.entity.*;
import com.liu.trachunom.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/dictionary_management/")
public class AdminControllerApi {

    private final QuocNguService quocNguService;
    private final CharacterService characterService;
    private final StructureService structureService;
    private final MeaningService meaningService;
    private final EntityService entityService;
    private final StandardisedService standardisedService;

    @GetMapping("characterDto")
    public CharacterDto characterDtoGet(@RequestParam(name = "value", required = false) String value) {
        CharacterX character = characterService.findByUnicode(value.codePointAt(0));
        CharacterDto characterDto = characterService.toDto(character);
        return characterDto;
    }

    @GetMapping("structureFormDto")
    public StructureFormDto structureFormDtoGet(@RequestParam(name = "id", required = false) Long id) {
        Structure structure = structureService.findById(id);
        StructureFormDto structureFormDto = structureService.toFormDto(structure);
        return structureFormDto;
    }

    @GetMapping("meaningDto")
    public MeaningDto meaningDtoGet(@RequestParam(name = "id", required = false) Long id) {
        Meaning meaning = meaningService.findById(id);
        MeaningDto meaningDto = meaningService.toDto(meaning);
        return  meaningDto;
    }

    @GetMapping("check_quoc_ngu")
    public boolean checkQuocNguGet(@RequestParam(name = "quocNgu", required = false) String quocNgu) {
        return quocNguService.existsByDescription(quocNgu);
    }

    @GetMapping("entityDto")
    public EntityDto entityDtoGet(@RequestParam(name = "id") Long id) {
        EntityX entity = entityService.findById(id);
        EntityDto entityDto = entityService.toDto(entity);
        return entityDto;
    }

    @GetMapping("standardisedDto")
    public StandardisedDto standardisedDtoGet(@RequestParam(name = "id", required = false) Long id) {
        StandardisedEntity standardisedEntity = standardisedService.findById(id);
        StandardisedDto standardisedDto = standardisedService.toDto(standardisedEntity);
        return standardisedDto;
    }

}
