import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type QuocNgu_1 from "./com/liu/trachunom/entity/pronunciation/QuocNgu.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("QuocNguService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("QuocNguService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<QuocNgu_1 | undefined> { return client_1.call("QuocNguService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<QuocNgu_1>> { return client_1.call("QuocNguService", "list", { pageable, filter }, init); }
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("QuocNguService", "delete", { id }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("QuocNguService", "deleteById", { id }, init); }
async function existsByDescription_1(description: string | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("QuocNguService", "existsByDescription", { description }, init); }
async function existsById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("QuocNguService", "existsById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<QuocNgu_1 | undefined> | undefined> { return client_1.call("QuocNguService", "findAll", {}, init); }
async function findAllPronunciations_1(init?: EndpointRequestInit_1): Promise<Array<string | undefined> | undefined> { return client_1.call("QuocNguService", "findAllPronunciations", {}, init); }
async function findByDescription_1(description: string | undefined, init?: EndpointRequestInit_1): Promise<QuocNgu_1 | undefined> { return client_1.call("QuocNguService", "findByDescription", { description }, init); }
async function findById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<QuocNgu_1 | undefined> { return client_1.call("QuocNguService", "findById", { id }, init); }
async function save_1(quocNgu: QuocNgu_1 | undefined, init?: EndpointRequestInit_1): Promise<QuocNgu_1 | undefined> { return client_1.call("QuocNguService", "save", { quocNgu }, init); }
export { count_1 as count, delete_1 as delete, deleteById_1 as deleteById, exists_1 as exists, existsByDescription_1 as existsByDescription, existsById_1 as existsById, findAll_1 as findAll, findAllPronunciations_1 as findAllPronunciations, findByDescription_1 as findByDescription, findById_1 as findById, get_1 as get, list_1 as list, save_1 as save };
