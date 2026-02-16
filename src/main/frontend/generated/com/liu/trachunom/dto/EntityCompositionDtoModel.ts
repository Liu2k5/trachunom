import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type EntityCompositionDto_1 from "./EntityCompositionDto.js";
import EntityDtoModel_1 from "./EntityDtoModel.js";
class EntityCompositionDtoModel<T extends EntityCompositionDto_1 = EntityCompositionDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(EntityCompositionDtoModel);
    get parentEntityId(): NumberModel_1 {
        return this[_getPropertyModel_1]("parentEntityId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get childEntityId(): NumberModel_1 {
        return this[_getPropertyModel_1]("childEntityId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get childEntity(): EntityDtoModel_1 {
        return this[_getPropertyModel_1]("childEntity", (parent, key) => new EntityDtoModel_1(parent, key, true));
    }
    get position(): NumberModel_1 {
        return this[_getPropertyModel_1]("position", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
}
export default EntityCompositionDtoModel;
