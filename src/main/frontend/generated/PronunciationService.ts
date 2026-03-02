import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Pronunciation_1 from "./com/liu/trachunom/entity/pronunciation/Pronunciation.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("PronunciationService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("PronunciationService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<Pronunciation_1 | undefined> { return client_1.call("PronunciationService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Pronunciation_1>> { return client_1.call("PronunciationService", "list", { pageable, filter }, init); }
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("PronunciationService", "delete", { id }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("PronunciationService", "deleteById", { id }, init); }
async function existsById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("PronunciationService", "existsById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Pronunciation_1 | undefined> | undefined> { return client_1.call("PronunciationService", "findAll", {}, init); }
async function findById_1(pronunciationId: number | undefined, init?: EndpointRequestInit_1): Promise<Pronunciation_1 | undefined> { return client_1.call("PronunciationService", "findById", { pronunciationId }, init); }
async function save_1(pronunciation: Pronunciation_1 | undefined, init?: EndpointRequestInit_1): Promise<Pronunciation_1 | undefined> { return client_1.call("PronunciationService", "save", { pronunciation }, init); }
export { count_1 as count, delete_1 as delete, deleteById_1 as deleteById, exists_1 as exists, existsById_1 as existsById, findAll_1 as findAll, findById_1 as findById, get_1 as get, list_1 as list, save_1 as save };
