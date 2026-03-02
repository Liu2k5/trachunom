import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type ExampleWordDto_1 from "./com/liu/trachunom/dto/ExampleWordDto.js";
import type ExampleWordId_1 from "./com/liu/trachunom/entity/example/ExampleWordId.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: ExampleWordId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExampleWordEndpoint", "delete", { id }, init); }
async function deleteByEachId_1(exampleId: number | undefined, entityId: number | undefined, position: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExampleWordEndpoint", "deleteByEachId", { exampleId, entityId, position }, init); }
async function findByExampleId_1(exampleId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<ExampleWordDto_1 | undefined> | undefined> { return client_1.call("ExampleWordEndpoint", "findByExampleId", { exampleId }, init); }
async function list_1(init?: EndpointRequestInit_1): Promise<Array<ExampleWordDto_1 | undefined> | undefined> { return client_1.call("ExampleWordEndpoint", "list", {}, init); }
async function save_1(exampleId: number | undefined, entityId: number | undefined, position: number | undefined, init?: EndpointRequestInit_1): Promise<ExampleWordDto_1 | undefined> { return client_1.call("ExampleWordEndpoint", "save", { exampleId, entityId, position }, init); }
export { delete_1 as delete, deleteByEachId_1 as deleteByEachId, findByExampleId_1 as findByExampleId, list_1 as list, save_1 as save };
