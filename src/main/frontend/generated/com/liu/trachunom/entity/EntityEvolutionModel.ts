import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type EntityEvolution_1 from "./EntityEvolution.js";
import EntityEvolutionIdModel_1 from "./EntityEvolutionIdModel.js";
import EntityXModel_1 from "./EntityXModel.js";
class EntityEvolutionModel<T extends EntityEvolution_1 = EntityEvolution_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(EntityEvolutionModel);
    get id(): EntityEvolutionIdModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new EntityEvolutionIdModel_1(parent, key, true));
    }
    get fromEntity(): EntityXModel_1 {
        return this[_getPropertyModel_1]("fromEntity", (parent, key) => new EntityXModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get toEntity(): EntityXModel_1 {
        return this[_getPropertyModel_1]("toEntity", (parent, key) => new EntityXModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get description(): StringModel_1 {
        return this[_getPropertyModel_1]("description", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default EntityEvolutionModel;
