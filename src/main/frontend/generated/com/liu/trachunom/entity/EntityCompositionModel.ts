import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type EntityComposition_1 from "./EntityComposition.js";
import EntityCompositionIdModel_1 from "./EntityCompositionIdModel.js";
import EntityXModel_1 from "./EntityXModel.js";
class EntityCompositionModel<T extends EntityComposition_1 = EntityComposition_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(EntityCompositionModel);
    get id(): EntityCompositionIdModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new EntityCompositionIdModel_1(parent, key, true));
    }
    get parentEntity(): EntityXModel_1 {
        return this[_getPropertyModel_1]("parentEntity", (parent, key) => new EntityXModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get childEntity(): EntityXModel_1 {
        return this[_getPropertyModel_1]("childEntity", (parent, key) => new EntityXModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
}
export default EntityCompositionModel;
