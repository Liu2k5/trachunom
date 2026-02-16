import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type EntityEvolutionDto_1 from "./com/liu/trachunom/dto/EntityEvolutionDto.js";
import client_1 from "./connect-client.default.js";
async function delete_1(fromEntityId: number | undefined, toEntityId: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityEvolutionEndpoint", "delete", { fromEntityId, toEntityId }, init); }
async function findByFromEntityId_1(fromEntityId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityEvolutionDto_1 | undefined> | undefined> { return client_1.call("EntityEvolutionEndpoint", "findByFromEntityId", { fromEntityId }, init); }
async function save_1(dto: EntityEvolutionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityEvolutionDto_1 | undefined> { return client_1.call("EntityEvolutionEndpoint", "save", { dto }, init); }
export { delete_1 as delete, findByFromEntityId_1 as findByFromEntityId, save_1 as save };
