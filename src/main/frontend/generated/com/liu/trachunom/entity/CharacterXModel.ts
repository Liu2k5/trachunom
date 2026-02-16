import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type CharacterX_1 from "./CharacterX.js";
import RadicalModel_1 from "./RadicalModel.js";
class CharacterXModel<T extends CharacterX_1 = CharacterX_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(CharacterXModel);
    get unicode(): NumberModel_1 {
        return this[_getPropertyModel_1]("unicode", (parent, key) => new NumberModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.Id" }], javaType: "java.lang.Integer" } }));
    }
    get radical(): RadicalModel_1 {
        return this[_getPropertyModel_1]("radical", (parent, key) => new RadicalModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get additionalStrokeNumber(): NumberModel_1 {
        return this[_getPropertyModel_1]("additionalStrokeNumber", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get totalStrokeNumber(): NumberModel_1 {
        return this[_getPropertyModel_1]("totalStrokeNumber", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get string(): StringModel_1 {
        return this[_getPropertyModel_1]("string", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get radicalString(): StringModel_1 {
        return this[_getPropertyModel_1]("radicalString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default CharacterXModel;
