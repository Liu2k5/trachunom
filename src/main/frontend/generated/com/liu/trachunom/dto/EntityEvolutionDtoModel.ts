import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import EntityDtoModel_1 from "./EntityDtoModel.js";
import type EntityEvolutionDto_1 from "./EntityEvolutionDto.js";
class EntityEvolutionDtoModel<T extends EntityEvolutionDto_1 = EntityEvolutionDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(EntityEvolutionDtoModel);
    get fromEntityId(): NumberModel_1 {
        return this[_getPropertyModel_1]("fromEntityId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get toEntityId(): NumberModel_1 {
        return this[_getPropertyModel_1]("toEntityId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get fromEntity(): EntityDtoModel_1 {
        return this[_getPropertyModel_1]("fromEntity", (parent, key) => new EntityDtoModel_1(parent, key, true));
    }
    get descriptionId(): NumberModel_1 {
        return this[_getPropertyModel_1]("descriptionId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get description(): StringModel_1 {
        return this[_getPropertyModel_1]("description", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default EntityEvolutionDtoModel;
