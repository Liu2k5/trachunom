import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import EntityXModel_1 from "./EntityXModel.js";
import ExampleModel_1 from "./ExampleModel.js";
import type ExampleWord_1 from "./ExampleWord.js";
import ExampleWordIdModel_1 from "./ExampleWordIdModel.js";
class ExampleWordModel<T extends ExampleWord_1 = ExampleWord_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(ExampleWordModel);
    get exampleWordId(): ExampleWordIdModel_1 {
        return this[_getPropertyModel_1]("exampleWordId", (parent, key) => new ExampleWordIdModel_1(parent, key, true));
    }
    get example(): ExampleModel_1 {
        return this[_getPropertyModel_1]("example", (parent, key) => new ExampleModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get entity(): EntityXModel_1 {
        return this[_getPropertyModel_1]("entity", (parent, key) => new EntityXModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
}
export default ExampleWordModel;
