import { _getPropertyModel as _getPropertyModel_1, ArrayModel as ArrayModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import ExplanationDtoModel_1 from "./ExplanationDtoModel.js";
import type MeaningDto_1 from "./MeaningDto.js";
import MeaningDtoModel_1 from "./MeaningDtoModel.js";
class MeaningDtoModel<T extends MeaningDto_1 = MeaningDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(MeaningDtoModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get origin(): MeaningDtoModel_1 {
        return this[_getPropertyModel_1]("origin", (parent, key) => new MeaningDtoModel_1(parent, key, true));
    }
    get explanations(): ArrayModel_1<ExplanationDtoModel_1> {
        return this[_getPropertyModel_1]("explanations", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new ExplanationDtoModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
    get explanationsString(): StringModel_1 {
        return this[_getPropertyModel_1]("explanationsString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default MeaningDtoModel;
