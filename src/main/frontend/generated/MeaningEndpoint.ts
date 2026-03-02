import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type MeaningDto_1 from "./com/liu/trachunom/dto/MeaningDto.js";
import type Meaning_1 from "./com/liu/trachunom/entity/meaning/Meaning.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("MeaningEndpoint", "delete", { id }, init); }
async function save_1(meaningDto: MeaningDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Meaning_1 | undefined> { return client_1.call("MeaningEndpoint", "save", { meaningDto }, init); }
export { delete_1 as delete, save_1 as save };
