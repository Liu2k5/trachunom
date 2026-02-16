import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type PronunciationEvolutionDto_1 from "./com/liu/trachunom/dto/PronunciationEvolutionDto.js";
import type PronunciationEvolutionId_1 from "./com/liu/trachunom/entity/PronunciationEvolutionId.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: PronunciationEvolutionId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("PronunciationEvolutionEndpoint", "delete", { id }, init); }
async function save_1(pronunciationEvolutionDto: PronunciationEvolutionDto_1 | undefined, init?: EndpointRequestInit_1): Promise<PronunciationEvolutionDto_1 | undefined> { return client_1.call("PronunciationEvolutionEndpoint", "save", { pronunciationEvolutionDto }, init); }
export { delete_1 as delete, save_1 as save };
