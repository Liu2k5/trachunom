import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type StructureComponentId_1 from "./StructureComponentId.js";
class StructureComponentIdModel<T extends StructureComponentId_1 = StructureComponentId_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(StructureComponentIdModel);
    get structureId(): NumberModel_1 {
        return this[_getPropertyModel_1]("structureId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get structureComponentId(): NumberModel_1 {
        return this[_getPropertyModel_1]("structureComponentId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get classificationId(): NumberModel_1 {
        return this[_getPropertyModel_1]("classificationId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default StructureComponentIdModel;
