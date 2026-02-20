import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Example_1 from "./com/liu/trachunom/entity/Example.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("ExampleService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("ExampleService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<Example_1 | undefined> { return client_1.call("ExampleService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Example_1>> { return client_1.call("ExampleService", "list", { pageable, filter }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExampleService", "deleteById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Example_1 | undefined> | undefined> { return client_1.call("ExampleService", "findAll", {}, init); }
async function findByEntityId_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<Array<Example_1 | undefined> | undefined> { return client_1.call("ExampleService", "findByEntityId", { id }, init); }
async function findById_1(exampleId: number | undefined, init?: EndpointRequestInit_1): Promise<Example_1 | undefined> { return client_1.call("ExampleService", "findById", { exampleId }, init); }
async function getHnomStringByExampleId_1(exampleId: number | undefined, init?: EndpointRequestInit_1): Promise<string | undefined> { return client_1.call("ExampleService", "getHnomStringByExampleId", { exampleId }, init); }
async function getQnguStringByExampleId_1(exampleId: number | undefined, init?: EndpointRequestInit_1): Promise<string | undefined> { return client_1.call("ExampleService", "getQnguStringByExampleId", { exampleId }, init); }
async function save_1(newExample: Example_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExampleService", "save", { newExample }, init); }
export { count_1 as count, deleteById_1 as deleteById, exists_1 as exists, findAll_1 as findAll, findByEntityId_1 as findByEntityId, findById_1 as findById, get_1 as get, getHnomStringByExampleId_1 as getHnomStringByExampleId, getQnguStringByExampleId_1 as getQnguStringByExampleId, list_1 as list, save_1 as save };
