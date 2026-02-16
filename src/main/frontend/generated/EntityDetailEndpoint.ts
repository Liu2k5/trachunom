import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type EntityDetailDto_1 from "./com/liu/trachunom/dto/EntityDetailDto.js";
import type EntityDto_1 from "./com/liu/trachunom/dto/EntityDto.js";
import type EntityEvolutionDto_1 from "./com/liu/trachunom/dto/EntityEvolutionDto.js";
import type StructureDto_1 from "./com/liu/trachunom/dto/StructureDto.js";
import client_1 from "./connect-client.default.js";
async function calcFractionByStructureId_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<number> { return client_1.call("EntityDetailEndpoint", "calcFractionByStructureId", { id }, init); }
async function getEntityDetail_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<EntityDetailDto_1 | undefined> { return client_1.call("EntityDetailEndpoint", "getEntityDetail", { id }, init); }
async function getEntityDtoByEntityId_1(entityId: number | undefined, init?: EndpointRequestInit_1): Promise<EntityDto_1 | undefined> { return client_1.call("EntityDetailEndpoint", "getEntityDtoByEntityId", { entityId }, init); }
async function getEntityEvolutionsByToEntityId_1(toEntityId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityEvolutionDto_1 | undefined> | undefined> { return client_1.call("EntityDetailEndpoint", "getEntityEvolutionsByToEntityId", { toEntityId }, init); }
async function getGridTemplateColumnsByStructureId_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<string | undefined> { return client_1.call("EntityDetailEndpoint", "getGridTemplateColumnsByStructureId", { id }, init); }
async function getStructureDtoByStructureId_1(structureId: number | undefined, init?: EndpointRequestInit_1): Promise<StructureDto_1 | undefined> { return client_1.call("EntityDetailEndpoint", "getStructureDtoByStructureId", { structureId }, init); }
async function getSynonymsByEntityId_1(entityId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityDto_1 | undefined> | undefined> { return client_1.call("EntityDetailEndpoint", "getSynonymsByEntityId", { entityId }, init); }
async function getVariancesByEntityId_1(entityId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<EntityDto_1 | undefined> | undefined> { return client_1.call("EntityDetailEndpoint", "getVariancesByEntityId", { entityId }, init); }
export { calcFractionByStructureId_1 as calcFractionByStructureId, getEntityDetail_1 as getEntityDetail, getEntityDtoByEntityId_1 as getEntityDtoByEntityId, getEntityEvolutionsByToEntityId_1 as getEntityEvolutionsByToEntityId, getGridTemplateColumnsByStructureId_1 as getGridTemplateColumnsByStructureId, getStructureDtoByStructureId_1 as getStructureDtoByStructureId, getSynonymsByEntityId_1 as getSynonymsByEntityId, getVariancesByEntityId_1 as getVariancesByEntityId };
