import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type PronunciationEvolutionId_1 from "./PronunciationEvolutionId.js";
class PronunciationEvolutionIdModel<T extends PronunciationEvolutionId_1 = PronunciationEvolutionId_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(PronunciationEvolutionIdModel);
    get fromPronunciationId(): NumberModel_1 {
        return this[_getPropertyModel_1]("fromPronunciationId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get toPronunciationId(): NumberModel_1 {
        return this[_getPropertyModel_1]("toPronunciationId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get string(): StringModel_1 {
        return this[_getPropertyModel_1]("string", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default PronunciationEvolutionIdModel;
