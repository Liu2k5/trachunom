import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Language_1 from "./com/liu/trachunom/entity/Language.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("LanguageService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("LanguageService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<Language_1 | undefined> { return client_1.call("LanguageService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Language_1>> { return client_1.call("LanguageService", "list", { pageable, filter }, init); }
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("LanguageService", "delete", { id }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("LanguageService", "deleteById", { id }, init); }
async function existsByAbbreviation_1(abbreviation: string | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("LanguageService", "existsByAbbreviation", { abbreviation }, init); }
async function existsById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("LanguageService", "existsById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Language_1 | undefined> | undefined> { return client_1.call("LanguageService", "findAll", {}, init); }
async function findById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<Language_1 | undefined> { return client_1.call("LanguageService", "findById", { id }, init); }
async function save_1(language: Language_1 | undefined, init?: EndpointRequestInit_1): Promise<Language_1 | undefined> { return client_1.call("LanguageService", "save", { language }, init); }
export { count_1 as count, delete_1 as delete, deleteById_1 as deleteById, exists_1 as exists, existsByAbbreviation_1 as existsByAbbreviation, existsById_1 as existsById, findAll_1 as findAll, findById_1 as findById, get_1 as get, list_1 as list, save_1 as save };
