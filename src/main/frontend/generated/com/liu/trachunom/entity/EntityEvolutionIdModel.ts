import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type EntityEvolutionId_1 from "./EntityEvolutionId.js";
class EntityEvolutionIdModel<T extends EntityEvolutionId_1 = EntityEvolutionId_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(EntityEvolutionIdModel);
    get fromEntityId(): NumberModel_1 {
        return this[_getPropertyModel_1]("fromEntityId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get toEntityId(): NumberModel_1 {
        return this[_getPropertyModel_1]("toEntityId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default EntityEvolutionIdModel;
