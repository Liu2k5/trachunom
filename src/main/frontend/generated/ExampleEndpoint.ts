import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type ExampleDto_1 from "./com/liu/trachunom/dto/ExampleDto.js";
import type Example_1 from "./com/liu/trachunom/entity/Example.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExampleEndpoint", "delete", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Example_1 | undefined> | undefined> { return client_1.call("ExampleEndpoint", "findAll", {}, init); }
async function getHnomString_1(example: Example_1 | undefined, init?: EndpointRequestInit_1): Promise<string | undefined> { return client_1.call("ExampleEndpoint", "getHnomString", { example }, init); }
async function getQnguString_1(example: Example_1 | undefined, init?: EndpointRequestInit_1): Promise<string | undefined> { return client_1.call("ExampleEndpoint", "getQnguString", { example }, init); }
async function save_1(exampleDto: ExampleDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Example_1 | undefined> { return client_1.call("ExampleEndpoint", "save", { exampleDto }, init); }
export { delete_1 as delete, findAll_1 as findAll, getHnomString_1 as getHnomString, getQnguString_1 as getQnguString, save_1 as save };
