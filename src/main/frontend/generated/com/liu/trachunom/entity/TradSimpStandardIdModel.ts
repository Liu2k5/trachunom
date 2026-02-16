import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type TradSimpStandardId_1 from "./TradSimpStandardId.js";
class TradSimpStandardIdModel<T extends TradSimpStandardId_1 = TradSimpStandardId_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(TradSimpStandardIdModel);
    get traditionalUnicode(): NumberModel_1 {
        return this[_getPropertyModel_1]("traditionalUnicode", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get simplifiedUnicode(): NumberModel_1 {
        return this[_getPropertyModel_1]("simplifiedUnicode", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
}
export default TradSimpStandardIdModel;
