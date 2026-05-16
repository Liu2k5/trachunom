import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type StructureComponentDto_1 from "./StructureComponentDto.js";
class StructureComponentDtoModel<T extends StructureComponentDto_1 = StructureComponentDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(StructureComponentDtoModel);
    get structureId(): NumberModel_1 {
        return this[_getPropertyModel_1]("structureId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get structureCharacterString(): StringModel_1 {
        return this[_getPropertyModel_1]("structureCharacterString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get structureComponentId(): NumberModel_1 {
        return this[_getPropertyModel_1]("structureComponentId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get structureComponentCharacterString(): StringModel_1 {
        return this[_getPropertyModel_1]("structureComponentCharacterString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get classificationId(): NumberModel_1 {
        return this[_getPropertyModel_1]("classificationId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get classificationDescription(): StringModel_1 {
        return this[_getPropertyModel_1]("classificationDescription", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get quantity(): NumberModel_1 {
        return this[_getPropertyModel_1]("quantity", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get position(): NumberModel_1 {
        return this[_getPropertyModel_1]("position", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default StructureComponentDtoModel;
