import { _getPropertyModel as _getPropertyModel_1, BooleanModel as BooleanModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type EntityX_1 from "./EntityX.js";
import LanguageModel_1 from "./LanguageModel.js";
import MeaningModel_1 from "./MeaningModel.js";
import PronunciationModel_1 from "./PronunciationModel.js";
import StructureModel_1 from "./StructureModel.js";
class EntityXModel<T extends EntityX_1 = EntityX_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(EntityXModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.Id" }], javaType: "java.lang.Long" } }));
    }
    get structure(): StructureModel_1 {
        return this[_getPropertyModel_1]("structure", (parent, key) => new StructureModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get pronunciation(): PronunciationModel_1 {
        return this[_getPropertyModel_1]("pronunciation", (parent, key) => new PronunciationModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get meaning(): MeaningModel_1 {
        return this[_getPropertyModel_1]("meaning", (parent, key) => new MeaningModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get language(): LanguageModel_1 {
        return this[_getPropertyModel_1]("language", (parent, key) => new LanguageModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get description(): StringModel_1 {
        return this[_getPropertyModel_1]("description", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get compound(): BooleanModel_1 {
        return this[_getPropertyModel_1]("compound", (parent, key) => new BooleanModel_1(parent, key, false, { meta: { javaType: "boolean" } }));
    }
    get attested(): BooleanModel_1 {
        return this[_getPropertyModel_1]("attested", (parent, key) => new BooleanModel_1(parent, key, false, { meta: { javaType: "boolean" } }));
    }
    get standardised(): BooleanModel_1 {
        return this[_getPropertyModel_1]("standardised", (parent, key) => new BooleanModel_1(parent, key, false, { meta: { javaType: "boolean" } }));
    }
    get explanationsString(): StringModel_1 {
        return this[_getPropertyModel_1]("explanationsString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get pronunciationString(): StringModel_1 {
        return this[_getPropertyModel_1]("pronunciationString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get characterString(): StringModel_1 {
        return this[_getPropertyModel_1]("characterString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get structureId(): StringModel_1 {
        return this[_getPropertyModel_1]("structureId", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default EntityXModel;
