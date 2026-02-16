import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import EntityDtoModel_1 from "./EntityDtoModel.js";
import type ExampleWordDto_1 from "./ExampleWordDto.js";
class ExampleWordDtoModel<T extends ExampleWordDto_1 = ExampleWordDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(ExampleWordDtoModel);
    get exampleId(): NumberModel_1 {
        return this[_getPropertyModel_1]("exampleId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get entityId(): NumberModel_1 {
        return this[_getPropertyModel_1]("entityId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get position(): NumberModel_1 {
        return this[_getPropertyModel_1]("position", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
    get entity(): EntityDtoModel_1 {
        return this[_getPropertyModel_1]("entity", (parent, key) => new EntityDtoModel_1(parent, key, true));
    }
}
export default ExampleWordDtoModel;
