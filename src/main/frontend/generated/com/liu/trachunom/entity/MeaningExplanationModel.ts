import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import ExplanationModel_1 from "./ExplanationModel.js";
import type MeaningExplanation_1 from "./MeaningExplanation.js";
import MeaningExplanationIdModel_1 from "./MeaningExplanationIdModel.js";
import MeaningModel_1 from "./MeaningModel.js";
class MeaningExplanationModel<T extends MeaningExplanation_1 = MeaningExplanation_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(MeaningExplanationModel);
    get id(): MeaningExplanationIdModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new MeaningExplanationIdModel_1(parent, key, true));
    }
    get meaning(): MeaningModel_1 {
        return this[_getPropertyModel_1]("meaning", (parent, key) => new MeaningModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get explanation(): ExplanationModel_1 {
        return this[_getPropertyModel_1]("explanation", (parent, key) => new ExplanationModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
}
export default MeaningExplanationModel;
