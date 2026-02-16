import { _getPropertyModel as _getPropertyModel_1, ArrayModel as ArrayModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type ExampleDto_1 from "./ExampleDto.js";
import ExampleWordDtoModel_1 from "./ExampleWordDtoModel.js";
class ExampleDtoModel<T extends ExampleDto_1 = ExampleDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(ExampleDtoModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get hnomString(): StringModel_1 {
        return this[_getPropertyModel_1]("hnomString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get qnguString(): StringModel_1 {
        return this[_getPropertyModel_1]("qnguString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get exampleWords(): ArrayModel_1<ExampleWordDtoModel_1> {
        return this[_getPropertyModel_1]("exampleWords", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new ExampleWordDtoModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
    get sourceName(): StringModel_1 {
        return this[_getPropertyModel_1]("sourceName", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get sourceDescription(): StringModel_1 {
        return this[_getPropertyModel_1]("sourceDescription", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get sourceId(): NumberModel_1 {
        return this[_getPropertyModel_1]("sourceId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
}
export default ExampleDtoModel;
