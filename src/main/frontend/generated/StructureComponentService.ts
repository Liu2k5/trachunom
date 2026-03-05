import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Structure_1 from "./com/liu/trachunom/entity/structure/Structure.js";
import type StructureComponent_1 from "./com/liu/trachunom/entity/structure/StructureComponent.js";
import type StructureComponentId_1 from "./com/liu/trachunom/entity/structure/StructureComponentId.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("StructureComponentService", "count", { filter }, init); }
async function exists_1(id: StructureComponentId_1, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("StructureComponentService", "exists", { id }, init); }
async function get_1(id: StructureComponentId_1, init?: EndpointRequestInit_1): Promise<StructureComponent_1 | undefined> { return client_1.call("StructureComponentService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<StructureComponent_1>> { return client_1.call("StructureComponentService", "list", { pageable, filter }, init); }
async function deleteById_1(id: StructureComponentId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("StructureComponentService", "deleteById", { id }, init); }
async function deleteByStructure_1(structure: Structure_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("StructureComponentService", "deleteByStructure", { structure }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<StructureComponent_1 | undefined> | undefined> { return client_1.call("StructureComponentService", "findAll", {}, init); }
async function findById_1(id: StructureComponentId_1 | undefined, init?: EndpointRequestInit_1): Promise<StructureComponent_1 | undefined> { return client_1.call("StructureComponentService", "findById", { id }, init); }
async function findByStructure_1(structure: Structure_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<StructureComponent_1 | undefined> | undefined> { return client_1.call("StructureComponentService", "findByStructure", { structure }, init); }
async function getStructureComponents_1(structure: Structure_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<StructureComponent_1 | undefined> | undefined> { return client_1.call("StructureComponentService", "getStructureComponents", { structure }, init); }
async function save_1(structureComponents: Array<StructureComponent_1 | undefined> | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("StructureComponentService", "save", { structureComponents }, init); }
export { count_1 as count, deleteById_1 as deleteById, deleteByStructure_1 as deleteByStructure, exists_1 as exists, findAll_1 as findAll, findById_1 as findById, findByStructure_1 as findByStructure, get_1 as get, getStructureComponents_1 as getStructureComponents, list_1 as list, save_1 as save };
