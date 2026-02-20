import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Pronunciation_1 from "./com/liu/trachunom/entity/Pronunciation.js";
import type PronunciationEvolution_1 from "./com/liu/trachunom/entity/PronunciationEvolution.js";
import type PronunciationEvolutionId_1 from "./com/liu/trachunom/entity/PronunciationEvolutionId.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("PronunciationEvolutionService", "count", { filter }, init); }
async function exists_1(id: PronunciationEvolutionId_1, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("PronunciationEvolutionService", "exists", { id }, init); }
async function get_1(id: PronunciationEvolutionId_1, init?: EndpointRequestInit_1): Promise<PronunciationEvolution_1 | undefined> { return client_1.call("PronunciationEvolutionService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<PronunciationEvolution_1>> { return client_1.call("PronunciationEvolutionService", "list", { pageable, filter }, init); }
async function delete_1(id: PronunciationEvolutionId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("PronunciationEvolutionService", "delete", { id }, init); }
async function deleteById_1(id: PronunciationEvolutionId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("PronunciationEvolutionService", "deleteById", { id }, init); }
async function existsById_1(id: PronunciationEvolutionId_1 | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("PronunciationEvolutionService", "existsById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<PronunciationEvolution_1 | undefined> | undefined> { return client_1.call("PronunciationEvolutionService", "findAll", {}, init); }
async function findByFromPronunciation_1(fromPronunciation: Pronunciation_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<PronunciationEvolution_1 | undefined> | undefined> { return client_1.call("PronunciationEvolutionService", "findByFromPronunciation", { fromPronunciation }, init); }
async function findByFromPronunciationId_1(fromPronunciationId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<PronunciationEvolution_1 | undefined> | undefined> { return client_1.call("PronunciationEvolutionService", "findByFromPronunciationId", { fromPronunciationId }, init); }
async function findById_1(id: PronunciationEvolutionId_1 | undefined, init?: EndpointRequestInit_1): Promise<PronunciationEvolution_1 | undefined> { return client_1.call("PronunciationEvolutionService", "findById", { id }, init); }
async function findByToPronunciation_1(fromPronunciation: Pronunciation_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<PronunciationEvolution_1 | undefined> | undefined> { return client_1.call("PronunciationEvolutionService", "findByToPronunciation", { fromPronunciation }, init); }
async function findByToPronunciationId_1(toPronunciationId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<PronunciationEvolution_1 | undefined> | undefined> { return client_1.call("PronunciationEvolutionService", "findByToPronunciationId", { toPronunciationId }, init); }
async function save_1(pronunciationEvolution: PronunciationEvolution_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("PronunciationEvolutionService", "save", { pronunciationEvolution }, init); }
export { count_1 as count, delete_1 as delete, deleteById_1 as deleteById, exists_1 as exists, existsById_1 as existsById, findAll_1 as findAll, findByFromPronunciation_1 as findByFromPronunciation, findByFromPronunciationId_1 as findByFromPronunciationId, findById_1 as findById, findByToPronunciation_1 as findByToPronunciation, findByToPronunciationId_1 as findByToPronunciationId, get_1 as get, list_1 as list, save_1 as save };
