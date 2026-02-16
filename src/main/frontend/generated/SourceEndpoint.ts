import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type SourceDto_1 from "./com/liu/trachunom/dto/SourceDto.js";
import type Source_1 from "./com/liu/trachunom/entity/Source.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("SourceEndpoint", "delete", { id }, init); }
async function save_1(sourceDto: SourceDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Source_1 | undefined> { return client_1.call("SourceEndpoint", "save", { sourceDto }, init); }
export { delete_1 as delete, save_1 as save };
