import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type ExampleWordId_1 from "./ExampleWordId.js";
class ExampleWordIdModel<T extends ExampleWordId_1 = ExampleWordId_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(ExampleWordIdModel);
    get exampleId(): NumberModel_1 {
        return this[_getPropertyModel_1]("exampleId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get entityId(): NumberModel_1 {
        return this[_getPropertyModel_1]("entityId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get position(): NumberModel_1 {
        return this[_getPropertyModel_1]("position", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default ExampleWordIdModel;
