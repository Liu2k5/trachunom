import { _getPropertyModel as _getPropertyModel_1, ArrayModel as ArrayModel_1, BooleanModel as BooleanModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import EntityCompositionDtoModel_1 from "./EntityCompositionDtoModel.js";
import type EntityDetailDto_1 from "./EntityDetailDto.js";
import EntityDtoModel_1 from "./EntityDtoModel.js";
import EntityEvolutionDtoModel_1 from "./EntityEvolutionDtoModel.js";
import ExampleDtoModel_1 from "./ExampleDtoModel.js";
import LanguageDtoModel_1 from "./LanguageDtoModel.js";
import MeaningDtoModel_1 from "./MeaningDtoModel.js";
import PronunciationDtoModel_1 from "./PronunciationDtoModel.js";
import StructureDtoModel_1 from "./StructureDtoModel.js";
class EntityDetailDtoModel<T extends EntityDetailDto_1 = EntityDetailDto_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(EntityDetailDtoModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { javaType: "java.lang.Long" } }));
    }
    get structure(): StructureDtoModel_1 {
        return this[_getPropertyModel_1]("structure", (parent, key) => new StructureDtoModel_1(parent, key, true));
    }
    get pronunciation(): PronunciationDtoModel_1 {
        return this[_getPropertyModel_1]("pronunciation", (parent, key) => new PronunciationDtoModel_1(parent, key, true));
    }
    get meaning(): MeaningDtoModel_1 {
        return this[_getPropertyModel_1]("meaning", (parent, key) => new MeaningDtoModel_1(parent, key, true));
    }
    get language(): LanguageDtoModel_1 {
        return this[_getPropertyModel_1]("language", (parent, key) => new LanguageDtoModel_1(parent, key, true));
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
    get compositions(): ArrayModel_1<EntityCompositionDtoModel_1> {
        return this[_getPropertyModel_1]("compositions", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new EntityCompositionDtoModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
    get evolutions(): ArrayModel_1<EntityEvolutionDtoModel_1> {
        return this[_getPropertyModel_1]("evolutions", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new EntityEvolutionDtoModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
    get synonyms(): ArrayModel_1<EntityDtoModel_1> {
        return this[_getPropertyModel_1]("synonyms", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new EntityDtoModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
    get variances(): ArrayModel_1<EntityDtoModel_1> {
        return this[_getPropertyModel_1]("variances", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new EntityDtoModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
    get examples(): ArrayModel_1<ExampleDtoModel_1> {
        return this[_getPropertyModel_1]("examples", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new ExampleDtoModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
    get compositionComponents(): ArrayModel_1<EntityDtoModel_1> {
        return this[_getPropertyModel_1]("compositionComponents", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new EntityDtoModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
}
export default EntityDetailDtoModel;
