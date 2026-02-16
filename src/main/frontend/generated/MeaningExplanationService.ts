import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Meaning_1 from "./com/liu/trachunom/entity/Meaning.js";
import type MeaningExplanation_1 from "./com/liu/trachunom/entity/MeaningExplanation.js";
import type MeaningExplanationId_1 from "./com/liu/trachunom/entity/MeaningExplanationId.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("MeaningExplanationService", "count", { filter }, init); }
async function exists_1(id: MeaningExplanationId_1, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("MeaningExplanationService", "exists", { id }, init); }
async function get_1(id: MeaningExplanationId_1, init?: EndpointRequestInit_1): Promise<MeaningExplanation_1 | undefined> { return client_1.call("MeaningExplanationService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<MeaningExplanation_1>> { return client_1.call("MeaningExplanationService", "list", { pageable, filter }, init); }
async function delete_1(id: MeaningExplanationId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("MeaningExplanationService", "delete", { id }, init); }
async function deleteById_1(id: MeaningExplanationId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("MeaningExplanationService", "deleteById", { id }, init); }
async function existsById_1(id: MeaningExplanationId_1 | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("MeaningExplanationService", "existsById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<MeaningExplanation_1 | undefined> | undefined> { return client_1.call("MeaningExplanationService", "findAll", {}, init); }
async function findById_1(id: MeaningExplanationId_1 | undefined, init?: EndpointRequestInit_1): Promise<MeaningExplanation_1 | undefined> { return client_1.call("MeaningExplanationService", "findById", { id }, init); }
async function findByMeaning_1(meaning: Meaning_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<MeaningExplanation_1 | undefined> | undefined> { return client_1.call("MeaningExplanationService", "findByMeaning", { meaning }, init); }
async function save_1(meaningExplanation: MeaningExplanation_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("MeaningExplanationService", "save", { meaningExplanation }, init); }
export { count_1 as count, delete_1 as delete, deleteById_1 as deleteById, exists_1 as exists, existsById_1 as existsById, findAll_1 as findAll, findById_1 as findById, findByMeaning_1 as findByMeaning, get_1 as get, list_1 as list, save_1 as save };
