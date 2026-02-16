import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type MeaningExplanationDto_1 from "./MeaningExplanationDto.js";
class MeaningExplanationDtoModel<T extends MeaningExplanationDto_1 = MeaningExplanationDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(MeaningExplanationDtoModel);
    get meaningId(): NumberModel_1 {
        return this[_getPropertyModel_1]("meaningId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get explanationId(): NumberModel_1 {
        return this[_getPropertyModel_1]("explanationId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default MeaningExplanationDtoModel;
