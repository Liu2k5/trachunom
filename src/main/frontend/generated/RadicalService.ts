import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Radical_1 from "./com/liu/trachunom/entity/Radical.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("RadicalService", "count", { filter }, init); }
async function exists_1(id: string, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("RadicalService", "exists", { id }, init); }
async function get_1(id: string, init?: EndpointRequestInit_1): Promise<Radical_1 | undefined> { return client_1.call("RadicalService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Radical_1>> { return client_1.call("RadicalService", "list", { pageable, filter }, init); }
async function delete_1(radical: Radical_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("RadicalService", "delete", { radical }, init); }
async function deleteById_1(id: string | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("RadicalService", "deleteById", { id }, init); }
async function existsById_1(string: string | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("RadicalService", "existsById", { string }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Radical_1 | undefined> | undefined> { return client_1.call("RadicalService", "findAll", {}, init); }
async function findById_1(id: string | undefined, init?: EndpointRequestInit_1): Promise<Radical_1 | undefined> { return client_1.call("RadicalService", "findById", { id }, init); }
async function findByUnicode_1(i: number, init?: EndpointRequestInit_1): Promise<Radical_1 | undefined> { return client_1.call("RadicalService", "findByUnicode", { i }, init); }
async function save_1(radical: Radical_1 | undefined, init?: EndpointRequestInit_1): Promise<Radical_1 | undefined> { return client_1.call("RadicalService", "save", { radical }, init); }
export { count_1 as count, delete_1 as delete, deleteById_1 as deleteById, exists_1 as exists, existsById_1 as existsById, findAll_1 as findAll, findById_1 as findById, findByUnicode_1 as findByUnicode, get_1 as get, list_1 as list, save_1 as save };
