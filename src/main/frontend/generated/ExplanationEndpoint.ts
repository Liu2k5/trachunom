import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type ExplanationDto_1 from "./com/liu/trachunom/dto/ExplanationDto.js";
import type Explanation_1 from "./com/liu/trachunom/entity/Explanation.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("ExplanationEndpoint", "delete", { id }, init); }
async function save_1(explanationDto: ExplanationDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Explanation_1 | undefined> { return client_1.call("ExplanationEndpoint", "save", { explanationDto }, init); }
export { delete_1 as delete, save_1 as save };
