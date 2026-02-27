import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type PronunciationDto_1 from "./PronunciationDto.js";
class PronunciationDtoModel<T extends PronunciationDto_1 = PronunciationDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(PronunciationDtoModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get quocNguId(): NumberModel_1 {
        return this[_getPropertyModel_1]("quocNguId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get pronunciationString(): StringModel_1 {
        return this[_getPropertyModel_1]("pronunciationString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get characterWithPronunciationsString(): StringModel_1 {
        return this[_getPropertyModel_1]("characterWithPronunciationsString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default PronunciationDtoModel;
