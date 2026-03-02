import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type CharacterDto_1 from "./com/liu/trachunom/dto/CharacterDto.js";
import type EntityCompositionDto_1 from "./com/liu/trachunom/dto/EntityCompositionDto.js";
import type EntityDto_1 from "./com/liu/trachunom/dto/EntityDto.js";
import type EntityEvolutionDto_1 from "./com/liu/trachunom/dto/EntityEvolutionDto.js";
import type ExampleDto_1 from "./com/liu/trachunom/dto/ExampleDto.js";
import type ExampleWordDto_1 from "./com/liu/trachunom/dto/ExampleWordDto.js";
import type ExplanationDto_1 from "./com/liu/trachunom/dto/ExplanationDto.js";
import type LanguageDto_1 from "./com/liu/trachunom/dto/LanguageDto.js";
import type MeaningDto_1 from "./com/liu/trachunom/dto/MeaningDto.js";
import type MeaningExplanationDto_1 from "./com/liu/trachunom/dto/MeaningExplanationDto.js";
import type PronunciationDto_1 from "./com/liu/trachunom/dto/PronunciationDto.js";
import type PronunciationEvolutionDto_1 from "./com/liu/trachunom/dto/PronunciationEvolutionDto.js";
import type QuocNguDto_1 from "./com/liu/trachunom/dto/QuocNguDto.js";
import type RadicalDto_1 from "./com/liu/trachunom/dto/RadicalDto.js";
import type SourceDto_1 from "./com/liu/trachunom/dto/SourceDto.js";
import type StructureClassificationDto_1 from "./com/liu/trachunom/dto/StructureClassificationDto.js";
import type StructureComponentDto_1 from "./com/liu/trachunom/dto/StructureComponentDto.js";
import type StructureDto_1 from "./com/liu/trachunom/dto/StructureDto.js";
import type StyleDto_1 from "./com/liu/trachunom/dto/StyleDto.js";
import type TradSimpStandardDto_1 from "./com/liu/trachunom/dto/TradSimpStandardDto.js";
import type CharacterX_1 from "./com/liu/trachunom/entity/character/CharacterX.js";
import type Radical_1 from "./com/liu/trachunom/entity/character/Radical.js";
import type TradSimpStandard_1 from "./com/liu/trachunom/entity/character/TradSimpStandard.js";
import type EntityComposition_1 from "./com/liu/trachunom/entity/entity/EntityComposition.js";
import type EntityEvolution_1 from "./com/liu/trachunom/entity/entity/EntityEvolution.js";
import type EntityX_1 from "./com/liu/trachunom/entity/entity/EntityX.js";
import type Example_1 from "./com/liu/trachunom/entity/example/Example.js";
import type ExampleWord_1 from "./com/liu/trachunom/entity/example/ExampleWord.js";
import type Language_1 from "./com/liu/trachunom/entity/Language.js";
import type Explanation_1 from "./com/liu/trachunom/entity/meaning/Explanation.js";
import type Meaning_1 from "./com/liu/trachunom/entity/meaning/Meaning.js";
import type MeaningExplanation_1 from "./com/liu/trachunom/entity/meaning/MeaningExplanation.js";
import type Pronunciation_1 from "./com/liu/trachunom/entity/pronunciation/Pronunciation.js";
import type PronunciationEvolution_1 from "./com/liu/trachunom/entity/pronunciation/PronunciationEvolution.js";
import type QuocNgu_1 from "./com/liu/trachunom/entity/pronunciation/QuocNgu.js";
import type Source_1 from "./com/liu/trachunom/entity/Source.js";
import type Structure_1 from "./com/liu/trachunom/entity/structure/Structure.js";
import type StructureClassification_1 from "./com/liu/trachunom/entity/structure/StructureClassification.js";
import type StructureComponent_1 from "./com/liu/trachunom/entity/structure/StructureComponent.js";
import type Style_1 from "./com/liu/trachunom/entity/Style.js";
import client_1 from "./connect-client.default.js";
async function getNewStructureComponentDto_1(init?: EndpointRequestInit_1): Promise<StructureComponentDto_1 | undefined> { return client_1.call("EntityMapper", "getNewStructureComponentDto", {}, init); }
async function getNewStructureComponentDtoWithStructureId_1(structureId: number | undefined, init?: EndpointRequestInit_1): Promise<StructureComponentDto_1 | undefined> { return client_1.call("EntityMapper", "getNewStructureComponentDtoWithStructureId", { structureId }, init); }
async function toCharacter_1(characterDto: CharacterDto_1 | undefined, init?: EndpointRequestInit_1): Promise<CharacterX_1 | undefined> { return client_1.call("EntityMapper", "toCharacter", { characterDto }, init); }
async function toCharacterDto_1(entity: CharacterX_1 | undefined, init?: EndpointRequestInit_1): Promise<CharacterDto_1 | undefined> { return client_1.call("EntityMapper", "toCharacterDto", { entity }, init); }
async function toCharacterDtoList_1(characters: Array<CharacterX_1 | undefined> | undefined, init?: EndpointRequestInit_1): Promise<Array<CharacterDto_1 | undefined> | undefined> { return client_1.call("EntityMapper", "toCharacterDtoList", { characters }, init); }
async function toDto_1(entity: EntityX_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityDto_1 | undefined> { return client_1.call("EntityMapper", "toDto", { entity }, init); }
async function toEntityComposition_1(dto: EntityCompositionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityComposition_1 | undefined> { return client_1.call("EntityMapper", "toEntityComposition", { dto }, init); }
async function toEntityCompositionDto_1(composition: EntityComposition_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityCompositionDto_1 | undefined> { return client_1.call("EntityMapper", "toEntityCompositionDto", { composition }, init); }
async function toEntityDto_1(entity: EntityX_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityDto_1 | undefined> { return client_1.call("EntityMapper", "toEntityDto", { entity }, init); }
async function toEntityDtoList_1(entities: Array<EntityX_1 | undefined> | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityDto_1 | undefined> | undefined> { return client_1.call("EntityMapper", "toEntityDtoList", { entities }, init); }
async function toEntityEvolution_1(dto: EntityEvolutionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityEvolution_1 | undefined> { return client_1.call("EntityMapper", "toEntityEvolution", { dto }, init); }
async function toEntityEvolutionDto_1(evolution: EntityEvolution_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityEvolutionDto_1 | undefined> { return client_1.call("EntityMapper", "toEntityEvolutionDto", { evolution }, init); }
async function toEntityX_1(entityDto: EntityDto_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityX_1 | undefined> { return client_1.call("EntityMapper", "toEntityX", { entityDto }, init); }
async function toExample_1(exampleDto: ExampleDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Example_1 | undefined> { return client_1.call("EntityMapper", "toExample", { exampleDto }, init); }
async function toExampleDto_1(example: Example_1 | undefined, init?: EndpointRequestInit_1): Promise<ExampleDto_1 | undefined> { return client_1.call("EntityMapper", "toExampleDto", { example }, init); }
async function toExampleWord_1(exampleWordDto: ExampleWordDto_1 | undefined, init?: EndpointRequestInit_1): Promise<ExampleWord_1 | undefined> { return client_1.call("EntityMapper", "toExampleWord", { exampleWordDto }, init); }
async function toExampleWordDto_1(exampleWord: ExampleWord_1 | undefined, init?: EndpointRequestInit_1): Promise<ExampleWordDto_1 | undefined> { return client_1.call("EntityMapper", "toExampleWordDto", { exampleWord }, init); }
async function toExplanation_1(explanationDto: ExplanationDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Explanation_1 | undefined> { return client_1.call("EntityMapper", "toExplanation", { explanationDto }, init); }
async function toExplanationDto_1(entity: Explanation_1 | undefined, init?: EndpointRequestInit_1): Promise<ExplanationDto_1 | undefined> { return client_1.call("EntityMapper", "toExplanationDto", { entity }, init); }
async function toLanguage_1(languageDto: LanguageDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Language_1 | undefined> { return client_1.call("EntityMapper", "toLanguage", { languageDto }, init); }
async function toLanguageDto_1(entity: Language_1 | undefined, init?: EndpointRequestInit_1): Promise<LanguageDto_1 | undefined> { return client_1.call("EntityMapper", "toLanguageDto", { entity }, init); }
async function toMeaning_1(meaningDto: MeaningDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Meaning_1 | undefined> { return client_1.call("EntityMapper", "toMeaning", { meaningDto }, init); }
async function toMeaningDto_1(meaning: Meaning_1 | undefined, init?: EndpointRequestInit_1): Promise<MeaningDto_1 | undefined> { return client_1.call("EntityMapper", "toMeaningDto", { meaning }, init); }
async function toMeaningExplanation_1(meaningExplanationDto: MeaningExplanationDto_1 | undefined, init?: EndpointRequestInit_1): Promise<MeaningExplanation_1 | undefined> { return client_1.call("EntityMapper", "toMeaningExplanation", { meaningExplanationDto }, init); }
async function toMeaningExplanationDto_1(meaningExplanation: MeaningExplanation_1 | undefined, init?: EndpointRequestInit_1): Promise<MeaningExplanationDto_1 | undefined> { return client_1.call("EntityMapper", "toMeaningExplanationDto", { meaningExplanation }, init); }
async function toPronunciation_1(pronunciationDto: PronunciationDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Pronunciation_1 | undefined> { return client_1.call("EntityMapper", "toPronunciation", { pronunciationDto }, init); }
async function toPronunciationDto_1(entity: Pronunciation_1 | undefined, init?: EndpointRequestInit_1): Promise<PronunciationDto_1 | undefined> { return client_1.call("EntityMapper", "toPronunciationDto", { entity }, init); }
async function toPronunciationEvolution_1(pronunciationEvolutionDto: PronunciationEvolutionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<PronunciationEvolution_1 | undefined> { return client_1.call("EntityMapper", "toPronunciationEvolution", { pronunciationEvolutionDto }, init); }
async function toPronunciationEvolutionDto_1(pronunciationEvolution: PronunciationEvolution_1 | undefined, init?: EndpointRequestInit_1): Promise<PronunciationEvolutionDto_1 | undefined> { return client_1.call("EntityMapper", "toPronunciationEvolutionDto", { pronunciationEvolution }, init); }
async function toQuocNgu_1(quocNguDto: QuocNguDto_1 | undefined, init?: EndpointRequestInit_1): Promise<QuocNgu_1 | undefined> { return client_1.call("EntityMapper", "toQuocNgu", { quocNguDto }, init); }
async function toQuocNguDto_1(quocNgu: QuocNgu_1 | undefined, init?: EndpointRequestInit_1): Promise<QuocNguDto_1 | undefined> { return client_1.call("EntityMapper", "toQuocNguDto", { quocNgu }, init); }
async function toRadical_1(radicalDto: RadicalDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Radical_1 | undefined> { return client_1.call("EntityMapper", "toRadical", { radicalDto }, init); }
async function toRadicalDto_1(radical: Radical_1 | undefined, init?: EndpointRequestInit_1): Promise<RadicalDto_1 | undefined> { return client_1.call("EntityMapper", "toRadicalDto", { radical }, init); }
async function toRadicalDtoList_1(radicals: Array<Radical_1 | undefined> | undefined, init?: EndpointRequestInit_1): Promise<Array<RadicalDto_1 | undefined> | undefined> { return client_1.call("EntityMapper", "toRadicalDtoList", { radicals }, init); }
async function toSource_1(sourceDto: SourceDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Source_1 | undefined> { return client_1.call("EntityMapper", "toSource", { sourceDto }, init); }
async function toSourceDto_1(source: Source_1 | undefined, init?: EndpointRequestInit_1): Promise<SourceDto_1 | undefined> { return client_1.call("EntityMapper", "toSourceDto", { source }, init); }
async function toStructure_1(structureDto: StructureDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Structure_1 | undefined> { return client_1.call("EntityMapper", "toStructure", { structureDto }, init); }
async function toStructureClassificationDto_1(entity: StructureClassification_1 | undefined, init?: EndpointRequestInit_1): Promise<StructureClassificationDto_1 | undefined> { return client_1.call("EntityMapper", "toStructureClassificationDto", { entity }, init); }
async function toStructureClassificationDtoList_1(structureClassifications: Array<StructureClassification_1 | undefined> | undefined, init?: EndpointRequestInit_1): Promise<Array<StructureClassificationDto_1 | undefined> | undefined> { return client_1.call("EntityMapper", "toStructureClassificationDtoList", { structureClassifications }, init); }
async function toStructureComponent_1(structureComponentDto: StructureComponentDto_1 | undefined, init?: EndpointRequestInit_1): Promise<StructureComponent_1 | undefined> { return client_1.call("EntityMapper", "toStructureComponent", { structureComponentDto }, init); }
async function toStructureComponentDto_1(structureComponent: StructureComponent_1 | undefined, init?: EndpointRequestInit_1): Promise<StructureComponentDto_1 | undefined> { return client_1.call("EntityMapper", "toStructureComponentDto", { structureComponent }, init); }
async function toStructureComponentDtoList_1(structureComponents: Array<StructureComponent_1 | undefined> | undefined, init?: EndpointRequestInit_1): Promise<Array<StructureComponentDto_1 | undefined> | undefined> { return client_1.call("EntityMapper", "toStructureComponentDtoList", { structureComponents }, init); }
async function toStructureDto_1(structure: Structure_1 | undefined, init?: EndpointRequestInit_1): Promise<StructureDto_1 | undefined> { return client_1.call("EntityMapper", "toStructureDto", { structure }, init); }
async function toStructureDtoList_1(structures: Array<Structure_1 | undefined> | undefined, init?: EndpointRequestInit_1): Promise<Array<StructureDto_1 | undefined> | undefined> { return client_1.call("EntityMapper", "toStructureDtoList", { structures }, init); }
async function toStyle_1(styleDto: StyleDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Style_1 | undefined> { return client_1.call("EntityMapper", "toStyle", { styleDto }, init); }
async function toStyleDto_1(style: Style_1 | undefined, init?: EndpointRequestInit_1): Promise<StyleDto_1 | undefined> { return client_1.call("EntityMapper", "toStyleDto", { style }, init); }
async function toTradSimpStandard_1(tradSimpStandardDto: TradSimpStandardDto_1 | undefined, init?: EndpointRequestInit_1): Promise<TradSimpStandard_1 | undefined> { return client_1.call("EntityMapper", "toTradSimpStandard", { tradSimpStandardDto }, init); }
async function toTradSimpStandardDto_1(tradSimpStandard: TradSimpStandard_1 | undefined, init?: EndpointRequestInit_1): Promise<TradSimpStandardDto_1 | undefined> { return client_1.call("EntityMapper", "toTradSimpStandardDto", { tradSimpStandard }, init); }
export { getNewStructureComponentDto_1 as getNewStructureComponentDto, getNewStructureComponentDtoWithStructureId_1 as getNewStructureComponentDtoWithStructureId, toCharacter_1 as toCharacter, toCharacterDto_1 as toCharacterDto, toCharacterDtoList_1 as toCharacterDtoList, toDto_1 as toDto, toEntityComposition_1 as toEntityComposition, toEntityCompositionDto_1 as toEntityCompositionDto, toEntityDto_1 as toEntityDto, toEntityDtoList_1 as toEntityDtoList, toEntityEvolution_1 as toEntityEvolution, toEntityEvolutionDto_1 as toEntityEvolutionDto, toEntityX_1 as toEntityX, toExample_1 as toExample, toExampleDto_1 as toExampleDto, toExampleWord_1 as toExampleWord, toExampleWordDto_1 as toExampleWordDto, toExplanation_1 as toExplanation, toExplanationDto_1 as toExplanationDto, toLanguage_1 as toLanguage, toLanguageDto_1 as toLanguageDto, toMeaning_1 as toMeaning, toMeaningDto_1 as toMeaningDto, toMeaningExplanation_1 as toMeaningExplanation, toMeaningExplanationDto_1 as toMeaningExplanationDto, toPronunciation_1 as toPronunciation, toPronunciationDto_1 as toPronunciationDto, toPronunciationEvolution_1 as toPronunciationEvolution, toPronunciationEvolutionDto_1 as toPronunciationEvolutionDto, toQuocNgu_1 as toQuocNgu, toQuocNguDto_1 as toQuocNguDto, toRadical_1 as toRadical, toRadicalDto_1 as toRadicalDto, toRadicalDtoList_1 as toRadicalDtoList, toSource_1 as toSource, toSourceDto_1 as toSourceDto, toStructure_1 as toStructure, toStructureClassificationDto_1 as toStructureClassificationDto, toStructureClassificationDtoList_1 as toStructureClassificationDtoList, toStructureComponent_1 as toStructureComponent, toStructureComponentDto_1 as toStructureComponentDto, toStructureComponentDtoList_1 as toStructureComponentDtoList, toStructureDto_1 as toStructureDto, toStructureDtoList_1 as toStructureDtoList, toStyle_1 as toStyle, toStyleDto_1 as toStyleDto, toTradSimpStandard_1 as toTradSimpStandard, toTradSimpStandardDto_1 as toTradSimpStandardDto };
