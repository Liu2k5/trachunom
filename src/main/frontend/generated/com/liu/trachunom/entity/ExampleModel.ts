import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import type Example_1 from "./Example.js";
import SourceModel_1 from "./SourceModel.js";
class ExampleModel<T extends Example_1 = Example_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(ExampleModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.Id" }], javaType: "java.lang.Long" } }));
    }
    get source(): SourceModel_1 {
        return this[_getPropertyModel_1]("source", (parent, key) => new SourceModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
}
export default ExampleModel;
