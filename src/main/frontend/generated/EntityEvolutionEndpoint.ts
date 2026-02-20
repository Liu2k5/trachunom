import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type EntityEvolutionDto_1 from "./com/liu/trachunom/dto/EntityEvolutionDto.js";
import type EntityEvolutionId_1 from "./com/liu/trachunom/entity/EntityEvolutionId.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: EntityEvolutionId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityEvolutionEndpoint", "delete", { id }, init); }
async function deleteByEachId_1(fromEntityId: number | undefined, toEntityId: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityEvolutionEndpoint", "deleteByEachId", { fromEntityId, toEntityId }, init); }
async function findByFromEntityId_1(fromEntityId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityEvolutionDto_1 | undefined> | undefined> { return client_1.call("EntityEvolutionEndpoint", "findByFromEntityId", { fromEntityId }, init); }
async function save_1(dto: EntityEvolutionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityEvolutionDto_1 | undefined> { return client_1.call("EntityEvolutionEndpoint", "save", { dto }, init); }
async function saveByEachId_1(fromEntityId: number | undefined, toEntityId: number | undefined, description: string | undefined, init?: EndpointRequestInit_1): Promise<EntityEvolutionDto_1 | undefined> { return client_1.call("EntityEvolutionEndpoint", "saveByEachId", { fromEntityId, toEntityId, description }, init); }
export { delete_1 as delete, deleteByEachId_1 as deleteByEachId, findByFromEntityId_1 as findByFromEntityId, save_1 as save, saveByEachId_1 as saveByEachId };
