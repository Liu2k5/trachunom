import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type EntityDto_1 from "./com/liu/trachunom/dto/EntityDto.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityEndpoint", "delete", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<EntityDto_1 | undefined> | undefined> { return client_1.call("EntityEndpoint", "findAll", {}, init); }
async function save_1(entityDto: EntityDto_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityDto_1 | undefined> { return client_1.call("EntityEndpoint", "save", { entityDto }, init); }
export { delete_1 as delete, findAll_1 as findAll, save_1 as save };
