import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type ExampleWord_1 from "./com/liu/trachunom/entity/ExampleWord.js";
import type ExampleWordId_1 from "./com/liu/trachunom/entity/ExampleWordId.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("ExampleWordService", "count", { filter }, init); }
async function exists_1(id: ExampleWordId_1, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("ExampleWordService", "exists", { id }, init); }
async function get_1(id: ExampleWordId_1, init?: EndpointRequestInit_1): Promise<ExampleWord_1 | undefined> { return client_1.call("ExampleWordService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<ExampleWord_1>> { return client_1.call("ExampleWordService", "list", { pageable, filter }, init); }
async function deleteById_1(id: ExampleWordId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExampleWordService", "deleteById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<ExampleWord_1 | undefined> | undefined> { return client_1.call("ExampleWordService", "findAll", {}, init); }
async function findByExampleId_1(exampleId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<ExampleWord_1 | undefined> | undefined> { return client_1.call("ExampleWordService", "findByExampleId", { exampleId }, init); }
async function findByExampleIdOrderByPosition_1(exampleId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<ExampleWord_1 | undefined> | undefined> { return client_1.call("ExampleWordService", "findByExampleIdOrderByPosition", { exampleId }, init); }
async function findById_1(id: ExampleWordId_1 | undefined, init?: EndpointRequestInit_1): Promise<ExampleWord_1 | undefined> { return client_1.call("ExampleWordService", "findById", { id }, init); }
async function save_1(exampleWord: ExampleWord_1 | undefined, init?: EndpointRequestInit_1): Promise<ExampleWord_1 | undefined> { return client_1.call("ExampleWordService", "save", { exampleWord }, init); }
export { count_1 as count, deleteById_1 as deleteById, exists_1 as exists, findAll_1 as findAll, findByExampleId_1 as findByExampleId, findByExampleIdOrderByPosition_1 as findByExampleIdOrderByPosition, findById_1 as findById, get_1 as get, list_1 as list, save_1 as save };
