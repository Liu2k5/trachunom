package com.liu.trachunom.mapper;

import com.liu.trachunom.dto.*;
import com.liu.trachunom.entity.*;
import com.liu.trachunom.entity.character.CharacterX;
import com.liu.trachunom.entity.character.Radical;
import com.liu.trachunom.entity.character.TradSimpStandard;
import com.liu.trachunom.entity.character.TradSimpStandardId;
import com.liu.trachunom.entity.entity.*;
import com.liu.trachunom.entity.example.Example;
import com.liu.trachunom.entity.example.ExampleWord;
import com.liu.trachunom.entity.example.ExampleWordId;
import com.liu.trachunom.entity.meaning.Explanation;
import com.liu.trachunom.entity.meaning.Meaning;
import com.liu.trachunom.entity.meaning.MeaningExplanation;
import com.liu.trachunom.entity.meaning.MeaningExplanationId;
import com.liu.trachunom.entity.pronunciation.Pronunciation;
import com.liu.trachunom.entity.pronunciation.PronunciationEvolution;
import com.liu.trachunom.entity.pronunciation.PronunciationEvolutionId;
import com.liu.trachunom.entity.pronunciation.QuocNgu;
import com.liu.trachunom.entity.structure.Structure;
import com.liu.trachunom.entity.structure.StructureClassification;
import com.liu.trachunom.entity.structure.StructureComponent;
import com.liu.trachunom.entity.structure.StructureComponentId;
import com.liu.trachunom.service.*;
import com.liu.trachunom.service.character.CharacterService;
import com.liu.trachunom.service.character.RadicalService;
import com.liu.trachunom.service.entity.EntityService;
import com.liu.trachunom.service.entity.EvolutionDescriptionService;
import com.liu.trachunom.service.example.ExampleService;
import com.liu.trachunom.service.example.ExampleWordService;
import com.liu.trachunom.service.meaning.ExplanationService;
import com.liu.trachunom.service.meaning.MeaningService;
import com.liu.trachunom.service.pronunciation.PronunciationEvolutionService;
import com.liu.trachunom.service.pronunciation.PronunciationService;
import com.liu.trachunom.service.pronunciation.QuocNguService;
import com.liu.trachunom.service.structure.StructureClassificationService;
import com.liu.trachunom.service.structure.StructureService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@BrowserCallable
@AnonymousAllowed
@RequiredArgsConstructor
@Slf4j
public class EntityMapper {
    private final EntityService entityService;
    private final ExampleService exampleService;
    private final ExampleWordService exampleWordService;
    private final RadicalService radicalService;
    private final CharacterService characterService;
    private final StructureService structureService;
    private final StructureClassificationService structureClassificationService;
    private final QuocNguService quocNguService;
    private final PronunciationService pronunciationService;
    private final MeaningService meaningService;
    private final ExplanationService explanationService;
    private final LanguageService languageService;
    private final SourceService sourceService;
    private final PronunciationEvolutionService pronunciationEvolutionService;
    private final EvolutionDescriptionService evolutionDescriptionService;

    public LanguageDto toLanguageDto(Language entity) {
        if (entity == null) {
            return null;
        }
        return LanguageDto.builder()
                .id(entity.getId())
                .abbreviation(entity.getAbbreviation())
                .build();
    }

    public ExplanationDto toExplanationDto(Explanation entity) {
        if (entity == null) {
            return null;
        }
        return ExplanationDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .build();
    }

    public MeaningDto toMeaningDto(Meaning meaning) {
        if (meaning == null) {
            return null;
        }

        List<ExplanationDto> explanationDtos = null;
        if (meaning.getExplanations() != null) {
            explanationDtos = meaning.getExplanations().stream()
                    .map(this::toExplanationDto)
                    .collect(Collectors.toList());
        }

        MeaningDto originDto = toMeaningDto(meaning.getOrigin());

        return MeaningDto.builder()
                .id(meaning.getId())
                .originId(meaning.getOrigin() != null ? meaning.getOrigin().getId() : null)
                .origin(originDto)
                .explanations(explanationDtos)
                .explanationsString(meaning.getExplanationsString())
                .build();
    }

    public CharacterDto toCharacterDto(CharacterX entity) {
        if (entity == null) {
            return null;
        }
        return CharacterDto.builder()
                .characterString(Character.toString(entity.getUnicode()))
                .radical(entity.getRadical() != null ? toRadicalDto(entity.getRadical()) : null)
                .additionalStrokeNumber(entity.getAdditionalStrokeNumber())
                .totalStrokeNumber(entity.getTotalStrokeNumber())
                .build();
    }

    public PronunciationDto toPronunciationDto(Pronunciation entity) {
        if (entity == null) {
            return null;
        }

        String pronunciationString = entity.getString();
        if (pronunciationString.trim().isEmpty()) {
            List<PronunciationEvolution> evolutions = pronunciationEvolutionService.findByFromPronunciation(entity);
            StringBuilder stringBuilder = new StringBuilder();

            if (evolutions.isEmpty()) {
                stringBuilder.append(" ");
            } else {
                stringBuilder.append("*");
                final boolean[] first = {true};
                evolutions.forEach(e -> {
                    String toPronunciationString = e.getToPronunciation().getString();
                    stringBuilder.append(first[0] ? " " : "-").append(toPronunciationString);
                    first[0] = false;
                });
            }
            pronunciationString = stringBuilder.toString();
        }

        StringBuilder stringBuilder1 = new StringBuilder();
        entityService.findByPronunciation(entity).forEach(e -> stringBuilder1.append(e != null
                ? entityService.getHnomStringById(e.getId())
                : "")
                .append(" "));


        return PronunciationDto.builder()
                .id(entity.getId())
                .quocNguId(entity.getQuocNgu() != null ? entity.getQuocNgu().getId() : null)
                .pronunciationString(pronunciationString)
                .characterWithPronunciationsString(pronunciationString + " " + stringBuilder1.toString().trim())
                .build();
    }

    public StructureClassificationDto toStructureClassificationDto(StructureClassification entity) {
        if (entity == null) {
            return null;
        }
        return StructureClassificationDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .build();
    }

    public StructureComponentDto toStructureComponentDto(StructureComponent structureComponent) {
        if (structureComponent == null) {
            return null;
        }
        return StructureComponentDto.builder()

                .structureId(structureComponent.getStructure() != null ? structureComponent.getStructure().getId() : null)
                .structureCharacterString(structureComponent.getStructure() != null ?
                        structureComponent.getStructure().getCharacterString() : null)
                .structureComponentId(structureComponent.getStructureComponent() != null ? structureComponent.getStructureComponent().getId() : null)
                .structureComponentCharacterString(structureComponent.getStructureComponent() != null ?
                        structureService.getCharacterStringById(structureComponent.getStructureComponent().getId()) : null)
                .classificationId(structureComponent.getStructureClassification() != null ? structureComponent.getStructureClassification().getId() : null)
                .classificationDescription(structureComponent.getStructureClassification() != null ?
                        structureComponent.getStructureClassification().getDescription() : null)
                .quantity(structureComponent.getQuantity())
                .build();
    }

    public StructureComponentDto getNewStructureComponentDtoWithStructureId(Long structureId) {
        return StructureComponentDto.builder()
                .structureId(structureId)
                .build();
    }

    public StructureComponentDto getNewStructureComponentDto() {
        return new StructureComponentDto();
    }

    public StructureDto toStructureDto(Structure structure) {
        if (structure == null) {
            return null;
        }

        String characterString;
        if (structure.getCharacter() != null) {
            characterString = structure.getCharacterString();
        } else {
            characterString = structureService.getCharacterStringById(structure.getId());
        }

        StringBuilder stringBuilder = new StringBuilder();
        entityService.findByStructure(structure).forEach(e -> stringBuilder.append(e != null
                ? entityService.getQnguStringById(e.getId())
                : "")
                .append(" "));

        return StructureDto.builder()
                .id(structure.getId())
                .character(toCharacterDto(structure.getCharacter()))
                .characterString(characterString)
                .characterWithPronunciationsString(characterString + " " + stringBuilder.toString().trim())
                .build();
    }

    public EntityDto toEntityDto(EntityX entity) {
        if (entity == null) {
            return new EntityDto();
        }

        String explanationsString = "";
        try {
            List<Explanation> explanations = entity.getMeaning().getExplanations();
            if (explanations != null && !explanations.isEmpty()) {
                explanationsString = explanations.getFirst().getDescription();
                if (explanations.size() > 1) {
                    explanationsString += " ...";
                }
            }
        } catch (NullPointerException ignored) {
        }

        return EntityDto.builder()
                .id(entity.getId())
                .structureId(entity.getStructure() == null ? null : entity.getStructure().getId())
                .pronunciationId(entity.getPronunciation() == null ? null : entity.getPronunciation().getId())
                .meaningId(entity.getMeaning() == null ? null : entity.getMeaning().getId())
                .languageId(entity.getLanguage() == null ? null : entity.getLanguage().getId())
                .description(entity.getDescription())
                .compound(entity.isCompound())
                .attested(entity.isAttested())
                .standardised(entity.isStandardised())
                .hnomString(entity.getId() == null ? "lazy load?" : entityService.getHnomStringById(entity.getId()))
                .qnguString(entity.getId() == null ? "lazy load?" : entityService.getQnguStringById(entity.getId()))
                .explanationsString(explanationsString)
                .build();
    }

    public EntityDto toDto(EntityX entity) {
        return toEntityDto(entity);
    }

    public List<EntityDto> toEntityDtoList(List<EntityX> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(this::toEntityDto)
                .collect(Collectors.toList());
    }

    public EntityCompositionDto toEntityCompositionDto(EntityComposition composition) {
        if (composition == null) {
            return null;
        }
        return EntityCompositionDto.builder()
                .parentEntityId(composition.getParentEntity().getId())
                .childEntityId(composition.getChildEntity().getId())
                .childEntity(toEntityDto(composition.getChildEntity()))
                .position(composition.getId().getPosition().intValue())
                .build();
    }

    public EntityEvolutionDto toEntityEvolutionDto(EntityEvolution evolution) {
        if (evolution == null) {
            return null;
        }

        EvolutionDescription evolutionDescription = evolution.getEvolutionDescription();
        return EntityEvolutionDto.builder()
                .fromEntityId(evolution.getFromEntity().getId())
                .toEntityId(evolution.getToEntity().getId())
                .description(evolutionDescription != null ? evolutionDescription.getDescription() : null)
                .descriptionId(evolutionDescription != null ? evolutionDescription.getId() : null)
                .fromEntity(toEntityDto(evolution.getFromEntity()))
                .build();
    }

    public ExampleDto toExampleDto(Example example) {
        if (example == null) {
            return null;
        }
        return ExampleDto.builder()
                .id(example.getId())
                .hnomString(exampleService.getHnomStringByExampleId(example.getId()))
                .qnguString(exampleService.getQnguStringByExampleId(example.getId()))
                .exampleWords(exampleWordService
                        .findByExampleId(example.getId())
                        .stream()
                        .sorted(Comparator.comparing(o -> o.getExampleWordId().getPosition()))
                        .map(this::toExampleWordDto)
                        .collect(Collectors.toList()))
                .sourceId(example.getSource() != null ? example.getSource().getId() : null)
                .sourceName(example.getSource() != null ? example.getSource().getName() : null)
                .sourceDescription(example.getSource() != null ? example.getSource().getDescription() : null)
                .build();
    }

    public ExampleWordDto toExampleWordDto(ExampleWord exampleWord) {
        if (exampleWord == null) {
            return null;
        }
        return ExampleWordDto.builder()
                .exampleId(exampleWord.getExample().getId())
                .entityId(exampleWord.getEntity().getId())
                .position(exampleWord.getExampleWordId().getPosition().intValue())
                .entity(toEntityDto(exampleWord.getEntity()))
                .build();
    }

    public List<RadicalDto> toRadicalDtoList(List<Radical> radicals) {
        if (radicals == null) {
            return new ArrayList<>();
        }
        return radicals.stream()
                .map(this::toRadicalDto)
                .collect(Collectors.toList());
    }

    public RadicalDto toRadicalDto(Radical radical) {
        if (radical == null) {
            return null;
        }
        return RadicalDto.builder()
                .id(radical.getId())
                .radicalUnicode(radical.getRadicalUnicode())
                .unicode(radical.getUnicode())
                .strokeNumber(radical.getStrokeNumber())
                .build();
    }

    public Radical toRadical(RadicalDto radicalDto) {
        if (radicalDto == null) {
            return null;
        }
        return Radical.builder()
                .id(radicalDto.getId())
                .radicalUnicode(radicalDto.getRadicalUnicode())
                .unicode(radicalDto.getUnicode())
                .strokeNumber(radicalDto.getStrokeNumber())
                .build();
    }

    public SourceDto toSourceDto(Source source) {
        if (source == null) {
            return null;
        }
        return SourceDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .startYear(source.getStartYear())
                .endYear(source.getEndYear())
                .build();
    }

    public Source toSource(SourceDto sourceDto) {
        if (sourceDto == null) {
            return null;
        }
        return Source.builder()
                .id(sourceDto.getId())
                .name(sourceDto.getName())
                .description(sourceDto.getDescription())
                .startYear(sourceDto.getStartYear())
                .endYear(sourceDto.getEndYear())
                .build();
    }

    public Language toLanguage(LanguageDto languageDto) {
        if (languageDto == null) {
            return null;
        }
        return Language.builder()
                .id(languageDto.getId())
                .abbreviation(languageDto.getAbbreviation())
                .build();
    }

    public StyleDto toStyleDto(Style style) {
        if (style == null) {
            return null;
        }
        return StyleDto.builder()
                .id(style.getId())
                .description(style.getDescription())
                .build();
    }

    public Style toStyle(StyleDto styleDto) {
        if (styleDto == null) {
            return null;
        }
        return Style.builder()
                .id(styleDto.getId())
                .description(styleDto.getDescription())
                .build();
    }

    public CharacterX toCharacter(CharacterDto characterDto) {
        if (characterDto == null) {
            return null;
        }
        return CharacterX.builder()
                .unicode(!characterDto.getCharacterString().isEmpty() ? characterDto.getCharacterString().codePointAt(0) : null)
                .radical(radicalService.findById(characterDto.getRadical().getId()))
                .additionalStrokeNumber(characterDto.getAdditionalStrokeNumber())
                .totalStrokeNumber(characterDto.getTotalStrokeNumber())
                .build();
    }

    public TradSimpStandard toTradSimpStandard(TradSimpStandardDto tradSimpStandardDto) {
        if (tradSimpStandardDto == null) {
            return null;
        }
        CharacterX traditionalCharacter = characterService.findByUnicode(!tradSimpStandardDto.getTraditionalString().isEmpty() ? tradSimpStandardDto.getTraditionalString().codePointAt(0) : null);
        CharacterX simplifiedCharacter = characterService.findByUnicode(!tradSimpStandardDto.getSimplifiedString().isEmpty() ? tradSimpStandardDto.getSimplifiedString().codePointAt(0) : null);

        return TradSimpStandard.builder()
                .id(TradSimpStandardId.builder()
                        .traditionalUnicode(!tradSimpStandardDto.getTraditionalString().isEmpty() ? tradSimpStandardDto.getTraditionalString().codePointAt(0) : null)
                        .simplifiedUnicode(!tradSimpStandardDto.getSimplifiedString().isEmpty() ? tradSimpStandardDto.getSimplifiedString().codePointAt(0) : null)
                        .build())
                .traditionalCharacter(traditionalCharacter)
                .simplifiedCharacter(simplifiedCharacter)
                .build();
    }

    public TradSimpStandardDto toTradSimpStandardDto(TradSimpStandard tradSimpStandard) {
        if (tradSimpStandard == null) {
            return null;
        }
        return TradSimpStandardDto.builder()
                .traditionalString(Character.toString(tradSimpStandard.getId().getTraditionalUnicode()))
                .simplifiedString(Character.toString(tradSimpStandard.getId().getSimplifiedUnicode()))
                .build();
    }

    public Structure toStructure(StructureDto structureDto) {
        if (structureDto == null) {
            return null;
        }

        CharacterX character = null;
        // Ưu tiên lấy từ unicode
        if (structureDto.getCharacterString() != null) {
            character = characterService.findByUnicode(!structureDto.getCharacterString().isEmpty() ? structureDto.getCharacterString().codePointAt(0) : null);
        } else if (structureDto.getCharacter() != null) {
            character = toCharacter(structureDto.getCharacter());
        }

        return Structure.builder()
                .id(structureDto.getId())
                .character(character)
                .build();
    }

    public StructureComponent toStructureComponent(StructureComponentDto structureComponentDto) {
        if (structureComponentDto == null) {
            return null;
        }

        StructureComponentId id = StructureComponentId.builder()
                .structureId(structureComponentDto.getStructureId())
                .structureComponentId(structureComponentDto.getStructureComponentId())
                .classificationId(structureComponentDto.getClassificationId())
                .build();

        return StructureComponent.builder()
                .id(id)
                .structure(structureComponentDto.getStructureId() != null ?
                    structureService.findById(structureComponentDto.getStructureId()) : null)
                .structureComponent(structureComponentDto.getStructureComponentId() != null ?
                    structureService.findById(structureComponentDto.getStructureComponentId()) : null)
                .structureClassification(structureComponentDto.getClassificationId() != null ?
                    structureClassificationService.findById(structureComponentDto.getClassificationId()) : null)
                .quantity(structureComponentDto.getQuantity())
                .build();
    }

    public List<CharacterDto> toCharacterDtoList(List<CharacterX> characters) {
        if (characters == null) {
            return new ArrayList<>();
        }
        return characters.stream()
                .map(this::toCharacterDto)
                .collect(Collectors.toList());
    }

    public List<StructureClassificationDto> toStructureClassificationDtoList(List<StructureClassification> structureClassifications) {
        if (structureClassifications == null) {
            return new ArrayList<>();
        }
        return structureClassifications.stream()
                .map(this::toStructureClassificationDto)
                .collect(Collectors.toList());
    }

    public List<StructureDto> toStructureDtoList(List<Structure> structures) {
        if  (structures == null) {
            return new ArrayList<>();
        }
        return structures.stream()
                .map(this::toStructureDto)
                .collect(Collectors.toList());
    }

    public List<StructureComponentDto> toStructureComponentDtoList(List<StructureComponent> structureComponents) {
        if (structureComponents == null) {
            return new ArrayList<>();
        }
        return structureComponents.stream()
                .map(this::toStructureComponentDto)
                .collect(Collectors.toList());
    }

    public Pronunciation toPronunciation(PronunciationDto pronunciationDto) {
        if (pronunciationDto == null) {
            return null;
        }
        return Pronunciation.builder()
                .id(pronunciationDto.getId())
                .quocNgu(quocNguService.findById(pronunciationDto.getQuocNguId()))
                .build();
    }

    public QuocNgu toQuocNgu(QuocNguDto quocNguDto) {
        if (quocNguDto == null) {
            return null;
        }
        return QuocNgu.builder()
                .id(quocNguDto.getId())
                .description(quocNguDto.getDescription())
                .build();
    }


    public QuocNguDto toQuocNguDto(QuocNgu quocNgu) {
        if (quocNgu == null) {
            return null;
        }
        return QuocNguDto.builder()
                .id(quocNgu.getId())
                .description(quocNgu.getDescription())
                .build();
    }

    public PronunciationEvolution toPronunciationEvolution(PronunciationEvolutionDto pronunciationEvolutionDto) {
        if (pronunciationEvolutionDto == null) {
            return null;
        }

        PronunciationEvolutionId id = PronunciationEvolutionId.builder()
                .fromPronunciationId(pronunciationEvolutionDto.getFromPronunciationId())
                .toPronunciationId(pronunciationEvolutionDto.getToPronunciationId())
                .build();
        Pronunciation fromPronunciation = pronunciationService.findById(pronunciationEvolutionDto.getFromPronunciationId());
        Pronunciation toPronunciation = pronunciationService.findById(pronunciationEvolutionDto.getToPronunciationId());
        return PronunciationEvolution.builder()
                .id(id)
                .fromPronunciation(fromPronunciation)
                .toPronunciation(toPronunciation)
                .build();
    }

    public PronunciationEvolutionDto toPronunciationEvolutionDto(PronunciationEvolution pronunciationEvolution) {
        if (pronunciationEvolution == null) {
            return null;
        }
        PronunciationDto fromDto = toPronunciationDto(pronunciationService.findById(pronunciationEvolution.getId().getFromPronunciationId()));
        PronunciationDto toDto = toPronunciationDto(pronunciationService.findById(pronunciationEvolution.getId().getToPronunciationId()));

        return PronunciationEvolutionDto.builder()
                .fromPronunciationId(pronunciationEvolution.getId().getFromPronunciationId())
                .toPronunciationId(pronunciationEvolution.getId().getToPronunciationId())
                .fromPronunciationString(fromDto.getPronunciationString())
                .toPronunciationString(toDto.getPronunciationString())
                .build();
    }

    public Explanation toExplanation(ExplanationDto explanationDto) {
        if (explanationDto == null) {
            return null;
        }
        return Explanation.builder()
                .id(explanationDto.getId())
                .description(explanationDto.getDescription())
                .build();
    }

    public Meaning toMeaning(MeaningDto meaningDto) {
        if (meaningDto == null) {
            return null;
        }

        List<Explanation> explanations = null;
        if (meaningDto.getExplanations() != null) {
            explanations = meaningDto.getExplanations().stream()
                    .map(this::toExplanation)
                    .collect(Collectors.toList());
        }

        Meaning origin = null;
        if (meaningDto.getOriginId() != null) {
            origin = meaningService.findById(meaningDto.getOriginId());
        }

        return Meaning.builder()
                .id(meaningDto.getId())
                .origin(origin)
                .explanations(explanations)
                .build();
    }

    public MeaningExplanation toMeaningExplanation(MeaningExplanationDto meaningExplanationDto) {
        if (meaningExplanationDto == null) {
            return null;
        }

        MeaningExplanationId id = MeaningExplanationId.builder()
                .meaningId(meaningExplanationDto.getMeaningId())
                .explanationId(meaningExplanationDto.getExplanationId())
                .build();
        Meaning meaning = meaningService.findById(meaningExplanationDto.getMeaningId());
        Explanation explanation = explanationService.findById(meaningExplanationDto.getExplanationId());
        return MeaningExplanation.builder()
                .id(id)
                .meaning(meaning)
                .explanation(explanation)
                .build();
    }

    public MeaningExplanationDto toMeaningExplanationDto(MeaningExplanation meaningExplanation) {
        if (meaningExplanation == null) {
            return null;
        }
        return MeaningExplanationDto.builder()
                .meaningId(meaningExplanation.getId() == null ? null : meaningExplanation.getId().getMeaningId())
                .explanationId(meaningExplanation.getId() == null ? null : meaningExplanation.getId().getExplanationId())
                .build();
    }

    public EntityX toEntityX(EntityDto entityDto) {
        if (entityDto == null) {
            return null;
        }
//
//        Structure structure = null;
//        if (entityDto.getStructure() != null && entityDto.getStructure().getId() != null) {
//            structure = structureService.findById(entityDto.getStructure().getId());
//        }
//
//        Pronunciation pronunciation = null;
//        if (entityDto.getPronunciation() != null && entityDto.getPronunciation().getId() != null) {
//            pronunciation = pronunciationService.findById(entityDto.getPronunciation().getId());
//        }
//
//        Meaning meaning = null;
//        if (entityDto.getMeaning() != null && entityDto.getMeaning().getId() != null) {
//            meaning = meaningService.findById(entityDto.getMeaning().getId()).orElse(null);
//        }
//
//        Language language = null;
//        if (entityDto.getLanguage() != null) {
//            language = toLanguage(entityDto.getLanguage());
//        }

        return EntityX.builder()
                .id(entityDto.getId())
                .structure(entityDto.getStructureId() != null ? structureService.findById(entityDto.getStructureId()) : null)
                .pronunciation(entityDto.getPronunciationId() != null ? pronunciationService.findById(entityDto.getPronunciationId()) : null)
                .meaning(entityDto.getMeaningId() != null ? meaningService.findById(entityDto.getMeaningId()) : null)
                .language(entityDto.getLanguageId() != null ? languageService.findById(entityDto.getLanguageId()) : null)
                .description(entityDto.getDescription())
                .compound(entityDto.isCompound())
                .attested(entityDto.isAttested())
                .standardised(entityDto.isStandardised())
                .build();
    }

    public EntityComposition toEntityComposition(EntityCompositionDto dto) {
        if (dto == null) {
            return null;
        }

        EntityX parentEntity = null;
        if (dto.getParentEntityId() != null) {
            parentEntity = entityService.findById(dto.getParentEntityId());
        }

        EntityX childEntity = null;
        if (dto.getChildEntityId() != null) {
            childEntity = entityService.findById(dto.getChildEntityId());
        }

        EntityCompositionId id = EntityCompositionId.builder()
                .parentEntityId(dto.getParentEntityId())
                .childEntityId(dto.getChildEntityId())
                .position(dto.getPosition() != null ? dto.getPosition().longValue() : 0L)
                .build();

        return EntityComposition.builder()
                .id(id)
                .parentEntity(parentEntity)
                .childEntity(childEntity)
                .build();
    }

    public EntityEvolution toEntityEvolution(EntityEvolutionDto dto) {
        if (dto == null) {
            return null;
        }

        EntityX fromEntity = null;
        if (dto.getFromEntityId() != null) {
            fromEntity = entityService.findById(dto.getFromEntityId());
        }

        EntityX toEntity = null;
        if (dto.getToEntityId() != null) {
            toEntity = entityService.findById(dto.getToEntityId());
        }

        EvolutionDescription evolutionDescription = null;
        if (dto.getDescriptionId() != null) {
            evolutionDescription = evolutionDescriptionService.findById(dto.getDescriptionId());
        }

        EntityEvolutionId id = EntityEvolutionId.builder()
                .fromEntityId(dto.getFromEntityId())
                .toEntityId(dto.getToEntityId())
                .descriptionId(dto.getDescriptionId())
                .build();

        log.debug("Mapping EntityEvolutionDto to EntityEvolution: fromEntityId={}, toEntityId={}, descriptionId={}",
                dto.getFromEntityId(), dto.getToEntityId(), dto.getDescriptionId());

        return EntityEvolution.builder()
                .id(id)
                .fromEntity(fromEntity)
                .toEntity(toEntity)
                .evolutionDescription(evolutionDescription)
                .build();
    }

    public ExampleWord toExampleWord(ExampleWordDto exampleWordDto) {
        if (exampleWordDto == null) {
            return null;
        }

        Example example = null;
        if (exampleWordDto.getExampleId() != null) {
            example = exampleService.findById(exampleWordDto.getExampleId());
        }
        EntityX entity = null;
        if (exampleWordDto.getEntityId() != null) {
            entity = entityService.findById(exampleWordDto.getEntityId());
        }
        return ExampleWord.builder()
                .exampleWordId(ExampleWordId.builder()
                        .exampleId(exampleWordDto.getExampleId())
                        .entityId(exampleWordDto.getEntityId())
                        .position(exampleWordDto.getPosition() != null ? exampleWordDto.getPosition().longValue() : 0L)
                        .build())
                .example(example)
                .entity(entity)
                .build();

    }

    public Example toExample(ExampleDto exampleDto) {
        if (exampleDto == null) {
            return null;
        }
        return Example.builder()
                .id(exampleDto.getId())
                .source(exampleDto.getSourceId() != null ? sourceService.findById(exampleDto.getSourceId()) : null)
                .build();
    }

    public List<PronunciationDto> toPronunciationDtoList(List<Pronunciation> pronunciations) {
        if (pronunciations == null) {
            return new ArrayList<>();
        }
        return pronunciations.stream()
                .map(this::toPronunciationDto)
                .collect(Collectors.toList());
    }

    public List<MeaningExplanationDto> toMeaningExplanationDtoList(List<MeaningExplanation> meaningExplanations) {
        if (meaningExplanations == null) {
            return new ArrayList<>();
        }
        return meaningExplanations.stream()
                .map(this::toMeaningExplanationDto)
                .collect(Collectors.toList());
    }

    public List<EntityComposition> toEntityCompositionList(List<EntityCompositionDto> compositionDtos) {
        if (compositionDtos == null) {
            return new ArrayList<>();
        }
        return compositionDtos.stream()
                .map(this::toEntityComposition)
                .collect(Collectors.toList());
    }

    public List<EntityEvolution> toEntityEvolutionList(List<EntityEvolutionDto> evolutionDtos) {
        if (evolutionDtos == null) {
            return new ArrayList<>();
        }
        return evolutionDtos.stream()
                .map(this::toEntityEvolution)
                .collect(Collectors.toList());
    }

    public List<ExampleWordDto> toExampleWordDtoList(List<ExampleWord> exampleWords) {
        if (exampleWords == null) {
            return new ArrayList<>();
        }
        return exampleWords.stream()
                .map(this::toExampleWordDto)
                .collect(Collectors.toList());
    }

    public List<PronunciationEvolutionDto> toPronunciationEvolutionDtoList(List<PronunciationEvolution> pronunciationEvolutions) {
        if (pronunciationEvolutions == null) {
            return new ArrayList<>();
        }
        return pronunciationEvolutions.stream()
                .map(this::toPronunciationEvolutionDto)
                .collect(Collectors.toList());
    }
}

