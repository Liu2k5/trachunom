import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type CharacterX_1 from "./com/liu/trachunom/entity/character/CharacterX.js";
import type EntityX_1 from "./com/liu/trachunom/entity/entity/EntityX.js";
import type Pronunciation_1 from "./com/liu/trachunom/entity/pronunciation/Pronunciation.js";
import type Structure_1 from "./com/liu/trachunom/entity/structure/Structure.js";
import type Filter_1 from "./com/vaadin/hilla/crud/filter/Filter.js";
import type Pageable_1 from "./com/vaadin/hilla/mappedtypes/Pageable.js";
import client_1 from "./connect-client.default.js";
async function count_1(filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("EntityService", "count", { filter }, init); }
async function exists_1(id: number, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("EntityService", "exists", { id }, init); }
async function get_1(id: number, init?: EndpointRequestInit_1): Promise<EntityX_1 | undefined> { return client_1.call("EntityService", "get", { id }, init); }
async function list_1(pageable: Pageable_1, filter: Filter_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1>> { return client_1.call("EntityService", "list", { pageable, filter }, init); }
async function deleteById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityService", "deleteById", { id }, init); }
async function existsById_1(entityId: number | undefined, init?: EndpointRequestInit_1): Promise<boolean> { return client_1.call("EntityService", "existsById", { entityId }, init); }
async function findAll_1(init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findAll", {}, init); }
async function findBeingSematicOrPhoneticComponentByEntityId_1(entityId: number | undefined, isSemantic: boolean, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findBeingSematicOrPhoneticComponentByEntityId", { entityId, isSemantic }, init); }
async function findByCharacter_1(character: CharacterX_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findByCharacter", { character }, init); }
async function findByCompound_1(b: boolean, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findByCompound", { b }, init); }
async function findById_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<EntityX_1 | undefined> { return client_1.call("EntityService", "findById", { id }, init); }
async function findByPronunciation_1(entity: Pronunciation_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findByPronunciation", { entity }, init); }
async function findByPronunciationId_1(pronunciationId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findByPronunciationId", { pronunciationId }, init); }
async function findByQuery_1(query: string | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findByQuery", { query }, init); }
async function findByStructure_1(structure: Structure_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findByStructure", { structure }, init); }
async function findHavingSameSemanticOrPhoneticComponentByEntityId_1(entityId: number | undefined, isSemantic: boolean, init?: EndpointRequestInit_1): Promise<Record<string, Array<EntityX_1 | undefined> | undefined> | undefined> { return client_1.call("EntityService", "findHavingSameSemanticOrPhoneticComponentByEntityId", { entityId, isSemantic }, init); }
async function findStandardByEntity_1(entity: EntityX_1 | undefined, init?: EndpointRequestInit_1): Promise<EntityX_1 | undefined> { return client_1.call("EntityService", "findStandardByEntity", { entity }, init); }
async function findSynonyms_1(entity: EntityX_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findSynonyms", { entity }, init); }
async function findVariants_1(entity: EntityX_1 | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityX_1 | undefined> | undefined> { return client_1.call("EntityService", "findVariants", { entity }, init); }
async function getHnomStringById_1(entityId: number | undefined, init?: EndpointRequestInit_1): Promise<string | undefined> { return client_1.call("EntityService", "getHnomStringById", { entityId }, init); }
async function getQnguStringById_1(entityId: number | undefined, init?: EndpointRequestInit_1): Promise<string | undefined> { return client_1.call("EntityService", "getQnguStringById", { entityId }, init); }
async function save_1(entity: EntityX_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("EntityService", "save", { entity }, init); }
export { count_1 as count, deleteById_1 as deleteById, exists_1 as exists, existsById_1 as existsById, findAll_1 as findAll, findBeingSematicOrPhoneticComponentByEntityId_1 as findBeingSematicOrPhoneticComponentByEntityId, findByCharacter_1 as findByCharacter, findByCompound_1 as findByCompound, findById_1 as findById, findByPronunciation_1 as findByPronunciation, findByPronunciationId_1 as findByPronunciationId, findByQuery_1 as findByQuery, findByStructure_1 as findByStructure, findHavingSameSemanticOrPhoneticComponentByEntityId_1 as findHavingSameSemanticOrPhoneticComponentByEntityId, findStandardByEntity_1 as findStandardByEntity, findSynonyms_1 as findSynonyms, findVariants_1 as findVariants, get_1 as get, getHnomStringById_1 as getHnomStringById, getQnguStringById_1 as getQnguStringById, list_1 as list, save_1 as save };
