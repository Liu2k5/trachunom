import { _getPropertyModel as _getPropertyModel_1, ArrayModel as ArrayModel_1, BooleanModel as BooleanModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type AuthInfo_1 from "./AuthInfo.js";
class AuthInfoModel<T extends AuthInfo_1 = AuthInfo_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(AuthInfoModel);
    get loggedIn(): BooleanModel_1 {
        return this[_getPropertyModel_1]("loggedIn", (parent, key) => new BooleanModel_1(parent, key, false, { meta: { javaType: "boolean" } }));
    }
    get username(): StringModel_1 {
        return this[_getPropertyModel_1]("username", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get roles(): ArrayModel_1<StringModel_1> {
        return this[_getPropertyModel_1]("roles", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }), { meta: { javaType: "java.util.List" } }));
    }
}
export default AuthInfoModel;
