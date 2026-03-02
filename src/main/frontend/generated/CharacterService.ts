import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type CharacterX_1 from "./com/liu/trachunom/entity/character/CharacterX.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("CharacterService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("CharacterService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<CharacterX_1 | undefined> { return client_1.call("CharacterService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<CharacterX_1>> { return client_1.call("CharacterService", "list", { pageable, filter }, init); }
async function delete_1(unicode: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("CharacterService", "delete", { unicode }, init); }
async function deleteById_1(unicode: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("CharacterService", "deleteById", { unicode }, init); }
async function deleteByUnicode_1(unicode: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("CharacterService", "deleteByUnicode", { unicode }, init); }
async function existsByUnicode_1(unicode: number | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("CharacterService", "existsByUnicode", { unicode }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<CharacterX_1 | undefined> | undefined> { return client_1.call("CharacterService", "findAll", {}, init); }
async function findByUnicode_1(unicode: number | undefined, init?: EndpointRequestInit_1): Promise<CharacterX_1 | undefined> { return client_1.call("CharacterService", "findByUnicode", { unicode }, init); }
async function save_1(character: CharacterX_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("CharacterService", "save", { character }, init); }
export { count_1 as count, delete_1 as delete, deleteById_1 as deleteById, deleteByUnicode_1 as deleteByUnicode, exists_1 as exists, existsByUnicode_1 as existsByUnicode, findAll_1 as findAll, findByUnicode_1 as findByUnicode, get_1 as get, list_1 as list, save_1 as save };
