import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Explanation_1 from "./com/liu/trachunom/entity/Explanation.js";
import type Meaning_1 from "./com/liu/trachunom/entity/Meaning.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("ExplanationService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("ExplanationService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<Explanation_1 | undefined> { return client_1.call("ExplanationService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Explanation_1>> { return client_1.call("ExplanationService", "list", { pageable, filter }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExplanationService", "deleteById", { id }, init); }
async function existsByDescription_1(string: string | undefined, explanation: Explanation_1 | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("ExplanationService", "existsByDescription", { string, explanation }, init); }
async function existsById_1(meaningId: number | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("ExplanationService", "existsById", { meaningId }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Explanation_1 | undefined> | undefined> { return client_1.call("ExplanationService", "findAll", {}, init); }
async function findById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<Explanation_1 | undefined> { return client_1.call("ExplanationService", "findById", { id }, init); }
async function findByMeaning_1(meaning: Meaning_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Explanation_1 | undefined> | undefined> { return client_1.call("ExplanationService", "findByMeaning", { meaning }, init); }
async function save_1(explanation: Explanation_1 | undefined, init?: EndpointRequestInit_1): Promise<Explanation_1 | undefined> { return client_1.call("ExplanationService", "save", { explanation }, init); }
export { count_1 as count, deleteById_1 as deleteById, exists_1 as exists, existsByDescription_1 as existsByDescription, existsById_1 as existsById, findAll_1 as findAll, findById_1 as findById, findByMeaning_1 as findByMeaning, get_1 as get, list_1 as list, save_1 as save };
