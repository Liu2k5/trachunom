import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Structure_1 from "./com/liu/trachunom/entity/structure/Structure.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("StructureService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("StructureService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<Structure_1 | undefined> { return client_1.call("StructureService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Structure_1>> { return client_1.call("StructureService", "list", { pageable, filter }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("StructureService", "deleteById", { id }, init); }
async function existsById_1(structureId: number | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("StructureService", "existsById", { structureId }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Structure_1 | undefined> | undefined> { return client_1.call("StructureService", "findAll", {}, init); }
async function findById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<Structure_1 | undefined> { return client_1.call("StructureService", "findById", { id }, init); }
async function getCharacterStringById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<string | undefined> { return client_1.call("StructureService", "getCharacterStringById", { id }, init); }
async function save_1(structure: Structure_1 | undefined, init?: EndpointRequestInit_1): Promise<Structure_1 | undefined> { return client_1.call("StructureService", "save", { structure }, init); }
export { count_1 as count, deleteById_1 as deleteById, exists_1 as exists, existsById_1 as existsById, findAll_1 as findAll, findById_1 as findById, get_1 as get, getCharacterStringById_1 as getCharacterStringById, list_1 as list, save_1 as save };
