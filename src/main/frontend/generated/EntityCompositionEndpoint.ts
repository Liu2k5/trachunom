import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type EntityCompositionDto_1 from "./com/liu/trachunom/dto/EntityCompositionDto.js";
import type EntityCompositionId_1 from "./com/liu/trachunom/entity/entity/EntityCompositionId.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: EntityCompositionId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityCompositionEndpoint", "delete", { id }, init); }
async function deleteByIds_1(parentEntityId: number | undefined, childEntityId: number | undefined, position: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityCompositionEndpoint", "deleteByIds", { parentEntityId, childEntityId, position }, init); }
async function findByParentEntityId_1(parentEntityId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityCompositionDto_1 | undefined> | undefined> { return client_1.call("EntityCompositionEndpoint", "findByParentEntityId", { parentEntityId }, init); }
async function save_1(dto: EntityCompositionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityCompositionDto_1 | undefined> { return client_1.call("EntityCompositionEndpoint", "save", { dto }, init); }
async function saveByIds_1(parentEntityId: number | undefined, childEntityId: number | undefined, position: number | undefined, init?: EndpointRequestInit_1): Promise<EntityCompositionDto_1 | undefined> { return client_1.call("EntityCompositionEndpoint", "saveByIds", { parentEntityId, childEntityId, position }, init); }
export { delete_1 as delete, deleteByIds_1 as deleteByIds, findByParentEntityId_1 as findByParentEntityId, save_1 as save, saveByIds_1 as saveByIds };
