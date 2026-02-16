import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type ExampleWordDto_1 from "./com/liu/trachunom/dto/ExampleWordDto.js";
import type ExampleWord_1 from "./com/liu/trachunom/entity/ExampleWord.js";
import client_1 from "./connect-client.default.js";
async function delete_1(exampleId: number | undefined, entityId: number | undefined, position: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExampleWordEndpoint", "delete", { exampleId, entityId, position }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<ExampleWord_1 | undefined> | undefined> { return client_1.call("ExampleWordEndpoint", "findAll", {}, init); }
async function findByExampleId_1(exampleId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<ExampleWord_1 | undefined> | undefined> { return client_1.call("ExampleWordEndpoint", "findByExampleId", { exampleId }, init); }
async function save_1(exampleId: number | undefined, entityId: number | undefined, position: number | undefined, init?: EndpointRequestInit_1): Promise<ExampleWordDto_1 | undefined> { return client_1.call("ExampleWordEndpoint", "save", { exampleId, entityId, position }, init); }
export { delete_1 as delete, findAll_1 as findAll, findByExampleId_1 as findByExampleId, save_1 as save };
