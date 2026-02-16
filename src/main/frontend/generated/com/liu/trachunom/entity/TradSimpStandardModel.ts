import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1 } from "@vaadin/hilla-lit-form";
import CharacterXModel_1 from "./CharacterXModel.js";
import type TradSimpStandard_1 from "./TradSimpStandard.js";
import TradSimpStandardIdModel_1 from "./TradSimpStandardIdModel.js";
class TradSimpStandardModel<T extends TradSimpStandard_1 = TradSimpStandard_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(TradSimpStandardModel);
    get id(): TradSimpStandardIdModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new TradSimpStandardIdModel_1(parent, key, true));
    }
    get traditionalCharacter(): CharacterXModel_1 {
        return this[_getPropertyModel_1]("traditionalCharacter", (parent, key) => new CharacterXModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get simplifiedCharacter(): CharacterXModel_1 {
        return this[_getPropertyModel_1]("simplifiedCharacter", (parent, key) => new CharacterXModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
}
export default TradSimpStandardModel;
