import { _getPropertyModel as _getPropertyModel_1, BooleanModel as BooleanModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type EntityDto_1 from "./EntityDto.js";
class EntityDtoModel<T extends EntityDto_1 = EntityDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(EntityDtoModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get structureId(): NumberModel_1 {
        return this[_getPropertyModel_1]("structureId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get pronunciationId(): NumberModel_1 {
        return this[_getPropertyModel_1]("pronunciationId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get meaningId(): NumberModel_1 {
        return this[_getPropertyModel_1]("meaningId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get languageId(): NumberModel_1 {
        return this[_getPropertyModel_1]("languageId", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get description(): StringModel_1 {
        return this[_getPropertyModel_1]("description", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get compound(): BooleanModel_1 {
        return this[_getPropertyModel_1]("compound", (parent, key) => new BooleanModel_1(parent, key, false, { meta: { javaType: "boolean" } }));
    }
    get attested(): BooleanModel_1 {
        return this[_getPropertyModel_1]("attested", (parent, key) => new BooleanModel_1(parent, key, false, { meta: { javaType: "boolean" } }));
    }
    get standardised(): BooleanModel_1 {
        return this[_getPropertyModel_1]("standardised", (parent, key) => new BooleanModel_1(parent, key, false, { meta: { javaType: "boolean" } }));
    }
    get hnomString(): StringModel_1 {
        return this[_getPropertyModel_1]("hnomString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get qnguString(): StringModel_1 {
        return this[_getPropertyModel_1]("qnguString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get explanationsString(): StringModel_1 {
        return this[_getPropertyModel_1]("explanationsString", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default EntityDtoModel;
