import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import StructureClassificationModel_1 from "./StructureClassificationModel.js";
import type StructureComponent_1 from "./StructureComponent.js";
import StructureComponentIdModel_1 from "./StructureComponentIdModel.js";
import StructureModel_1 from "./StructureModel.js";
class StructureComponentModel<T extends StructureComponent_1 = StructureComponent_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(StructureComponentModel);
    get id(): StructureComponentIdModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new StructureComponentIdModel_1(parent, key, true));
    }
    get structure(): StructureModel_1 {
        return this[_getPropertyModel_1]("structure", (parent, key) => new StructureModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get structureComponent(): StructureModel_1 {
        return this[_getPropertyModel_1]("structureComponent", (parent, key) => new StructureModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get structureClassification(): StructureClassificationModel_1 {
        return this[_getPropertyModel_1]("structureClassification", (parent, key) => new StructureClassificationModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get quantity(): NumberModel_1 {
        return this[_getPropertyModel_1]("quantity", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Integer" } }));
    }
}
export default StructureComponentModel;
