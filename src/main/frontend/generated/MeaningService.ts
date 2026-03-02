import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Meaning_1 from "./com/liu/trachunom/entity/meaning/Meaning.js";
import type Pronunciation_1 from "./com/liu/trachunom/entity/pronunciation/Pronunciation.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("MeaningService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("MeaningService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<Meaning_1 | undefined> { return client_1.call("MeaningService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Meaning_1>> { return client_1.call("MeaningService", "list", { pageable, filter }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("MeaningService", "deleteById", { id }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<Meaning_1 | undefined> | undefined> { return client_1.call("MeaningService", "findAll", {}, init); }
async function findAllWithPronunciation_1(pronunciation: Pronunciation_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<Meaning_1 | undefined> | undefined> { return client_1.call("MeaningService", "findAllWithPronunciation", { pronunciation }, init); }
async function findAllWithPronunciationId_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<Array<Meaning_1 | undefined> | undefined> { return client_1.call("MeaningService", "findAllWithPronunciationId", { id }, init); }
async function findById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<Meaning_1 | undefined> { return client_1.call("MeaningService", "findById", { id }, init); }
async function save_1(meaning: Meaning_1 | undefined, init?: EndpointRequestInit_1): Promise<Meaning_1 | undefined> { return client_1.call("MeaningService", "save", { meaning }, init); }
export { count_1 as count, deleteById_1 as deleteById, exists_1 as exists, findAll_1 as findAll, findAllWithPronunciation_1 as findAllWithPronunciation, findAllWithPronunciationId_1 as findAllWithPronunciationId, findById_1 as findById, get_1 as get, list_1 as list, save_1 as save };
