import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type PronunciationEvolutionDto_1 from "./PronunciationEvolutionDto.js";
class PronunciationEvolutionDtoModel<T extends PronunciationEvolutionDto_1 = PronunciationEvolutionDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(PronunciationEvolutionDtoModel);
    get fromPronunciationId(): NumberModel_1 {
        return this[_getPropertyModel_1]("fromPronunciationId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get toPronunciationId(): NumberModel_1 {
        return this[_getPropertyModel_1]("toPronunciationId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default PronunciationEvolutionDtoModel;
