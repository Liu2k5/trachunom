import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type MeaningExplanationDto_1 from "./com/liu/trachunom/dto/MeaningExplanationDto.js";
import client_1 from "./connect-client.default.js";
async function delete_1(meaningId: number | undefined, explanationId: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("MeaningExplanationEndpoint", "delete", { meaningId, explanationId }, init); }
async function findByMeaningId_1(meaningId: number | undefined, init?: EndpointRequestInit_1): Promise<Array<MeaningExplanationDto_1 | undefined> | undefined> { return client_1.call("MeaningExplanationEndpoint", "findByMeaningId", { meaningId }, init); }
async function save_1(meaningExplanationDto: MeaningExplanationDto_1 | undefined, init?: EndpointRequestInit_1): Promise<MeaningExplanationDto_1 | undefined> { return client_1.call("MeaningExplanationEndpoint", "save", { meaningExplanationDto }, init); }
export { delete_1 as delete, findByMeaningId_1 as findByMeaningId, save_1 as save };
