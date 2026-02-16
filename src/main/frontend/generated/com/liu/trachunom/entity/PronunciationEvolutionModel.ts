import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type PronunciationEvolution_1 from "./PronunciationEvolution.js";
import PronunciationEvolutionIdModel_1 from "./PronunciationEvolutionIdModel.js";
import PronunciationModel_1 from "./PronunciationModel.js";
class PronunciationEvolutionModel<T extends PronunciationEvolution_1 = PronunciationEvolution_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(PronunciationEvolutionModel);
    get id(): PronunciationEvolutionIdModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new PronunciationEvolutionIdModel_1(parent, key, true));
    }
    get fromPronunciation(): PronunciationModel_1 {
        return this[_getPropertyModel_1]("fromPronunciation", (parent, key) => new PronunciationModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get toPronunciation(): PronunciationModel_1 {
        return this[_getPropertyModel_1]("toPronunciation", (parent, key) => new PronunciationModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
}
export default PronunciationEvolutionModel;
