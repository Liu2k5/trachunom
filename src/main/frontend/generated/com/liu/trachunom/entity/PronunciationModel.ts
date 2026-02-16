import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type Pronunciation_1 from "./Pronunciation.js";
import QuocNguModel_1 from "./QuocNguModel.js";
class PronunciationModel<T extends Pronunciation_1 = Pronunciation_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(PronunciationModel);
    get id(): NumberModel_1 {
        return this[_getPropertyModel_1]("id", (parent, key) => new NumberModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.Id" }], javaType: "java.lang.Long" } }));
    }
    get quocNgu(): QuocNguModel_1 {
        return this[_getPropertyModel_1]("quocNgu", (parent, key) => new QuocNguModel_1(parent, key, true, { meta: { annotations: [{ name: "jakarta.persistence.ManyToOne" }] } }));
    }
    get string(): StringModel_1 {
        return this[_getPropertyModel_1]("string", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
}
export default PronunciationModel;
