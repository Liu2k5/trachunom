import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type TradSimpStandardDto_1 from "./TradSimpStandardDto.js";
class TradSimpStandardDtoModel<T extends TradSimpStandardDto_1 = TradSimpStandardDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(TradSimpStandardDtoModel);
    get traditionalString(): StringModel_1 {
        return this[_getPropertyModel_1]("traditionalString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get simplifiedString(): StringModel_1 {
        return this[_getPropertyModel_1]("simplifiedString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default TradSimpStandardDtoModel;
