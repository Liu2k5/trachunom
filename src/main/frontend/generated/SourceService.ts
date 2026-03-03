import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Source_1 from "./com/liu/trachunom/entity/Source.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("SourceService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("SourceService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<Source_1 | undefined> { return client_1.call("SourceService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Source_1>> { return client_1.call("SourceService", "list", { pageable, filter }, init); }
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("SourceService", "delete", { id }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("SourceService", "deleteById", { id }, init); }
async function existsById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("SourceService", "existsById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Source_1 | undefined> | undefined> { return client_1.call("SourceService", "findAll", {}, init); }
async function findByEntityId_1(entityId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<Source_1 | undefined> | undefined> { return client_1.call("SourceService", "findByEntityId", { entityId }, init); }
async function findById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<Source_1 | undefined> { return client_1.call("SourceService", "findById", { id }, init); }
async function save_1(source: Source_1 | undefined, init?: EndpointRequestInit_1): Promise<Source_1 | undefined> { return client_1.call("SourceService", "save", { source }, init); }
export { count_1 as count, delete_1 as delete, deleteById_1 as deleteById, exists_1 as exists, existsById_1 as existsById, findAll_1 as findAll, findByEntityId_1 as findByEntityId, findById_1 as findById, get_1 as get, list_1 as list, save_1 as save };
