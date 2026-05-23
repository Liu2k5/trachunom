import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type StructureComponentDto_1 from "./com/liu/trachunom/dto/StructureComponentDto.js";
import type StructureDto_1 from "./com/liu/trachunom/dto/StructureDto.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("StructureEndpoint", "delete", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<StructureDto_1 | undefined> | undefined> { return client_1.call("StructureEndpoint", "findAll", {}, init); }
async function findStructureById_1(structureId: number | undefined, init?: EndpointRequestInit_1): Promise<StructureDto_1 | undefined> { return client_1.call("StructureEndpoint", "findStructureById", { structureId }, init); }
async function findStructureComponentsByStructureId_1(structureId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<StructureComponentDto_1 | undefined> | undefined> { return client_1.call("StructureEndpoint", "findStructureComponentsByStructureId", { structureId }, init); }
async function save_1(structureDto: StructureDto_1 | undefined, init?: EndpointRequestInit_1): Promise<StructureDto_1 | undefined> { return client_1.call("StructureEndpoint", "save", { structureDto }, init); }
export { delete_1 as delete, findAll_1 as findAll, findStructureById_1 as findStructureById, findStructureComponentsByStructureId_1 as findStructureComponentsByStructureId, save_1 as save };
