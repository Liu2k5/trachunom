import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type EntityCompositionDto_1 from "./com/liu/trachunom/dto/EntityCompositionDto.js";
import client_1 from "./connect-client.default.js";
async function delete_1(parentEntityId: number | undefined, childEntityId: number | undefined, position: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityCompositionEndpoint", "delete", { parentEntityId, childEntityId, position }, init); }
async function findByParentEntityId_1(parentEntityId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityCompositionDto_1 | undefined> | undefined> { return client_1.call("EntityCompositionEndpoint", "findByParentEntityId", { parentEntityId }, init); }
async function save_1(dto: EntityCompositionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityCompositionDto_1 | undefined> { return client_1.call("EntityCompositionEndpoint", "save", { dto }, init); }
export { delete_1 as delete, findByParentEntityId_1 as findByParentEntityId, save_1 as save };
