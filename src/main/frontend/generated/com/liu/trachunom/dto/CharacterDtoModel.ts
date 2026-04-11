import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type CharacterDto_1 from "./CharacterDto.js";
import RadicalDtoModel_1 from "./RadicalDtoModel.js";
class CharacterDtoModel<T extends CharacterDto_1 = CharacterDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(CharacterDtoModel);
    get characterString(): StringModel_1 {
        return this[_getPropertyModel_1]("characterString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get radical(): RadicalDtoModel_1 {
        return this[_getPropertyModel_1]("radical", (parent, key) => new RadicalDtoModel_1(parent, key, true));
    }
    get additionalStrokeNumber(): NumberModel_1 {
        return this[_getPropertyModel_1]("additionalStrokeNumber", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get totalStrokeNumber(): NumberModel_1 {
        return this[_getPropertyModel_1]("totalStrokeNumber", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get radicalString(): StringModel_1 {
        return this[_getPropertyModel_1]("radicalString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get radicalStringByUnicode(): StringModel_1 {
        return this[_getPropertyModel_1]("radicalStringByUnicode", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default CharacterDtoModel;
