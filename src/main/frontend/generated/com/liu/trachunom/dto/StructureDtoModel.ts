import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import CharacterDtoModel_1 from "./CharacterDtoModel.js";
import type StructureDto_1 from "./StructureDto.js";
class StructureDtoModel<T extends StructureDto_1 = StructureDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(StructureDtoModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get character(): CharacterDtoModel_1 {
        return this[_getPropertyModel_1]("character", (parent, key) => new CharacterDtoModel_1(parent, key, true));
    }
    get structureTypeId(): NumberModel_1 {
        return this[_getPropertyModel_1]("structureTypeId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get width(): NumberModel_1 {
        return this[_getPropertyModel_1]("width", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Double" } }));
    }
    get height(): NumberModel_1 {
        return this[_getPropertyModel_1]("height", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Double" } }));
    }
    get innerWidth(): NumberModel_1 {
        return this[_getPropertyModel_1]("innerWidth", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Double" } }));
    }
    get innerHeight(): NumberModel_1 {
        return this[_getPropertyModel_1]("innerHeight", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Double" } }));
    }
    get characterString(): StringModel_1 {
        return this[_getPropertyModel_1]("characterString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get characterWithPronunciationsString(): StringModel_1 {
        return this[_getPropertyModel_1]("characterWithPronunciationsString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default StructureDtoModel;
