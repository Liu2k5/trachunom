import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type Radical_1 from "./Radical.js";
class RadicalModel<T extends Radical_1 = Radical_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(RadicalModel);
    get id(): StringModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new StringModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.Id" }], javaType: "java.lang.String" } }));
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
export default RadicalModel;
