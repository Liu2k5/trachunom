import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type RadicalDto_1 from "./RadicalDto.js";
class RadicalDtoModel<T extends RadicalDto_1 = RadicalDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(RadicalDtoModel);
    get id(): StringModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get radicalUnicode(): NumberModel_1 {
        return this[_getPropertyModel_1]("radicalUnicode", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get unicode(): NumberModel_1 {
        return this[_getPropertyModel_1]("unicode", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get strokeNumber(): NumberModel_1 {
        return this[_getPropertyModel_1]("strokeNumber", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get string(): StringModel_1 {
        return this[_getPropertyModel_1]("string", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default RadicalDtoModel;
